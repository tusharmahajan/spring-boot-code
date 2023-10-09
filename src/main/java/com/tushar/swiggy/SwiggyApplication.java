package com.tushar.swiggy;

import com.tushar.swiggy.convertingConfigtoJavaObject.WhatsAppConfig;
import com.tushar.swiggy.managingObjectUsingBean.AlgoSimulator;
import com.tushar.swiggy.managingObjectUsingBean.AlgoStrategy;
import com.tushar.swiggy.managingObjectUsingBean.SearchService;
import com.tushar.swiggy.managingObjectUsingComponet.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class SwiggyApplication implements ApplicationRunner {

	@Autowired
	private Notification notification;

//	@Value("${wa.hostName.company:travel}")  // adding default value as travel for this property
//	private String companyName;

	@Value("#{'${wa.hostName.companyType:}'.split('!')}")
	private List<String> companyType;

	public static void main(String[] args) {
//		System.out.println("Message 1");
		ApplicationContext context = SpringApplication.run(SwiggyApplication.class, args);
//		Arrays.stream(context.getBeanDefinitionNames()).forEach(s -> System.out.println(s));
//		AlgoStrategy algoStrategy = context.getBean(AlgoStrategy.class);
//		algoStrategy.searchData();

		WhatsAppConfig whatsAppConfig = context.getBean(WhatsAppConfig.class);
		System.out.println(whatsAppConfig.getHostName().getCompanyType());
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
//		System.out.println("Message 2");
//		notification.sendMessage("hi there");
//		System.out.println(companyName);

	}

}
