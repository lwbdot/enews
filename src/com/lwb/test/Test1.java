package com.lwb.test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//import com.lwb.services.interfaces.XDepartmentServiceInter;
//import com.lwb.services.interfaces.XEmployeeServiceInter;
//import com.lwb.ssh.domain.Department;
//import com.lwb.ssh.domain.Employee;
import com.lwb.services.interfaces.NewsCommentServiceInter;
import com.lwb.services.interfaces.NewsDetailServiceInter;
import com.lwb.services.interfaces.NewsServiceInter;
import com.lwb.services.interfaces.UserInfoServiceInter;
import com.lwb.ssh.domain.News;
import com.lwb.ssh.domain.NewsComment;
import com.lwb.ssh.domain.NewsDetail;
import com.lwb.ssh.domain.UserInfo;
import com.lwb.test.beans.TestUser;

public class Test1 {
	public static void main(String[] args) {
		ApplicationContext ac = 
			new ClassPathXmlApplicationContext("applicationContext.xml");
		
		NewsServiceInter nsi = (NewsServiceInter)ac.getBean("ns");
		News news = (News) nsi.getObjectById(News.class, 80);
		Date date = new Date();
		news.setNewsExtra(date.toString());
		nsi.updateObject(news);
		
		// 下面为数据库添加测试数据
		/*
		NewsCommentServiceInter ncs = (NewsCommentServiceInter)ac.getBean("ncs");
		NewsComment nc = new NewsComment();
		nc.setNewsCommentContent("评论测试4");
		nc.setNewsCommentDate(new Date());
		nc.setNewsCommenterId(1);
		nc.setNewsCommentStatus(1);
		nc.setNewsExtra("0");
		nc.setNewsId(1);
		ncs.addObject(nc);
		*/
		/*
		UserInfoServiceInter uis = (UserInfoServiceInter)ac.getBean("uis");
		UserInfo user = new UserInfo();
		user.setUserEmail("abc2@abc.com");
		user.setUserLevel(2);
		user.setUserName("jack");
		user.setUserPass("123456");
		user.setUserRealName("王五");
		user.setUserRigisterDate(new Date());
		uis.addObject(user);
		*/
		/*
		NewsServiceInter ns = (NewsServiceInter) ac.getBean("ns");
		NewsDetailServiceInter nds = (NewsDetailServiceInter) ac.getBean("nds");
		String con = "" +
		    "希沙姆丁强调指出，国际组织和机构正在帮助马来西亚进行分析，目前马方在和法国的营救队进行接触，" +
		    "当年法航447航班坠海，这家营救队帮助法航找到了坠海的飞机。对此，过去经验告诉他要谨慎，" +
		    "2009年法航447飞机失踪后，花了两年时间才找到黑匣子，对此，搜寻行动将会考虑采用深海搜寻，" +
		    "包括使用声呐技术。走廊搜救“仍将持续”“北部走廊国家会在自己领域搜寻航路和航空领域，" +
		    "他们通过国际联络组织机构取得联系”，希沙姆丁称，目前中国动用了一切可能手段，" +
		    "包括21颗卫星，此外还向有需要的地区派遣更多船只和飞机。柬埔寨动用4架直升机，老挝空军也在搜索，" +
		    "新加坡利用其国际信息融汇中心通知海员并帮助搜索，泰国军方正在泰国北部搜索，" +
		    "越南也在领土范围内继续搜索，英国计划派遣一艘船只前往南部走廊搜索，马来西亚将向哈萨克斯坦派遣两架飞机。";
		for(int i = 41;i<81;i++){
			News news = new News();
			news.setNewsExtra("0");
			news.setNewsPublishDate(new Date());
			news.setNewsTitle("测试新闻shfuaeufhaskldfhaewufha"+i);
			news.setNewsTypeId(3);
			news.setNewsWrittenDate(new Date());
			ns.addObject(news);
			
			NewsDetail dn = new NewsDetail();
			dn.setNewsAttachPath("0");
			dn.setNewsClassify("学院公告");
			dn.setNewsContent(con);
			dn.setNewsFlag(1);
			dn.setNewsId(i);
			dn.setNewsPicPath("0");
			dn.setNewsReadTimes(100+i);
			dn.setNewsWriterId(1);
			nds.addObject(dn);
		}
		;*/
		
		
		
		/*   //测试Spring环境是否搭好
		TestUser user = (TestUser) ac.getBean("user");
		System.out.println(user.getName());
		*/
		
		/*  // 测试hibernate和Spring结合是否成功
		SessionFactory factory = (SessionFactory) ac.getBean("sessionFactory");
		Session session = factory.openSession();
		Employee employee = new Employee("Jack","123@abc",new Date(),new Float(1000));
		Transaction ts = session.beginTransaction();
		session.save(employee);
		ts.commit();
		*/
		
		// 测试使用接口来实现添加对象是否成功
		/*
		EmployeeServiceInter employeeSvc = 
			(EmployeeServiceInter) ac.getBean("employeeService");
		Employee employee = new Employee("Lily","Lucy@abc",new Date(),1000f,"123",1);
		employeeSvc.addEmployee(employee);
		*/
		
		
		// hibernate添加带有外键的数据到数据表
/*		XEmployeeServiceInter esi = 
			(XEmployeeServiceInter) ac.getBean("employeeService");
		XDepartmentServiceInter dsi = 
			(XDepartmentServiceInter) ac.getBean("departmentService");
		Employee employee1 = new Employee("Lily","Lucy@abc",new Date(),1000f,"123",1);
		Employee employee2 = new Employee("Jack","Jack@abc",new Date(),1000f,"123",1);
		Employee employee3 = new Employee("Tony","Tony@abc",new Date(),1000f,"123",1);
		
		esi.addEmployee(employee3);
		esi.addEmployee(employee2);
		esi.addEmployee(employee1);
		
		Department department = new Department();
		department.setName("财务部");
		
		dsi.addDepartment(department);
*/
	}
}
