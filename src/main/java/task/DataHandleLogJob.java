package task;

import com.mongodb.*;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.data.mongodb.core.MongoTemplate;
import util.SpringBeanUtil;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**********************************
 * 日志轨迹处理类
 * @author GWB
 * 之前写的日志处理类，用到了mongo的 mapReduce函数,还是有一些参考借鉴意义的。
 * ********************************
 */
public class DataHandleLogJob implements Job {
    private static final int MAX_HANDLE_STEP = 2;

    @Resource
    private MongoTemplate mongoTemplate;


    /**
     * 定时任务，将库中的数据groupby并判断是否处理成功后存入dbsLogResult库中。
     */
    public void work() {
        String handleResult;
//        MongoTemplate mongoTemplate = SpringBeanUtil.getBean("dbsLog");
        DBCollection dbsLog = (DBCollection) mongoTemplate.getCollection("dbsLog");
        String map = " function() {\n" +
                "emit(this.dataFlowId, {handleType:[this.handleType]});};";
        String reduce = "function(key, values) {\n" +
                "var ht = {handleType:[]}\n" +
                "for(var i = 0; i < values.length; i++){\n" +
                "ht.handleType.push(values[i].handleType[0]);\n" +
                "}\n" +
                "return ht;\n" +
                "};";
        MapReduceCommand cmd = new MapReduceCommand(dbsLog, map, reduce,
                null, MapReduceCommand.OutputType.INLINE, null);
        MapReduceOutput out = dbsLog.mapReduce(cmd);
        Iterable<DBObject> it = out.results();
        //组装处理结果
        List<BasicDBObject> dbsLogResult = new ArrayList<BasicDBObject>();
        for (DBObject dbObject : it) {
            String dataFlowId = (String) dbObject.get("_id");
            DBObject value = (DBObject) dbObject.get("value");
            BasicDBList handleType = (BasicDBList) value.get("handleType");
            String dataHandleFlow = "";
            for (Object s : handleType) {
                dataHandleFlow += s + "-";
            }
            dataHandleFlow = dataHandleFlow.substring(0, dataHandleFlow.length() - 1);
            dataHandleFlow = dataHandleFlow.replaceAll("org.bson.BsonUndefined@0", "null");
            handleResult = this.handleResult(dataHandleFlow);
            BasicDBObject document = new BasicDBObject();
            document.put("_id", dataFlowId);
            document.put("dataHandleFlow", dataHandleFlow);
            document.put("handleResult", handleResult);
            dbsLogResult.add(document);
            this.insertDbsLogResult(dbsLogResult);
        }
    }

    /**
     * 判断处理结果
     *
     * @param dataHandleFlow
     * @return
     */
    public String handleResult(String dataHandleFlow) {
        String handleResult;
        String[] result = dataHandleFlow.split("-");
        String lastHandle = result[0];
        if (result.length == MAX_HANDLE_STEP) {
            if (lastHandle.equals("delete") || lastHandle.equals("update")) {
                handleResult = "true";
            } else {
                handleResult = "false";
            }
        } else {
            if (!lastHandle.equals("delete")) {
                handleResult = "false";
            } else {
                handleResult = "true";
            }
        }
        return handleResult;
    }

    /**
     * 没有则插入，有则更新
     *
     * @param list
     */
    public void insertDbsLogResult(List<BasicDBObject> list) {
        MongoTemplate mongoTemplate = SpringBeanUtil.getBean("dbsLog");
        DBCollection course = (DBCollection) mongoTemplate.getCollection("dbsLogResult");
        DBCursor dbCursor = course.find();
        for (BasicDBObject result : list) {
            course.insert(result);
            while (dbCursor.hasNext()) {
                course.update(result, dbCursor.next(), true, false);
            }
        }
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        work();
    }
}
