package spring.SpringBoot;

/*
springboot配置拦截器等代码，为了演示ThreadLocal
 */
//@SpringBootApplication
//public class ConcurrencyApplication extends WebMvcConfigurerAdapter {
//
//	public static void main(String[] args) {
//		SpringApplication.run(ConcurrencyApplication.class, args);
//	}
//
//	@Bean
//	public FilterRegistrationBean httpFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		registrationBean.setFilter(new HttpFilter());
//		registrationBean.addUrlPatterns("/threadLocal/*");
//		return registrationBean;
//	}
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");
//	}
//}
