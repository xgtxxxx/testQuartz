

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) { 

		   System.out.println("测试任务调度开始..."); 

		   ApplicationContext context = new ClassPathXmlApplicationContext( 

		     "applicationContext-jdbc.xml"); 

		   // 如果配置文件中将startQuertz bean的lazy-init设置为false 则不用实例化 

		   // context.getBean("startQuertz"); 

		   System.out.print("测试任务调度结束!\n"); 

		} 


}
