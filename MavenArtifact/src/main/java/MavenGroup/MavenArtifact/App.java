package MavenGroup.MavenArtifact;

import org.springframework.context.support.GenericXmlApplicationContext;

public class App
{
    public static void main(String[] args)
    {
	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("beanConfig.xml");
	A a = ctx.getBean("a", A.class);
	A b = ctx.getBean("a", A.class);
	System.out.println(a == b);

	A c = ctx.getBean("b", A.class);
	A d = ctx.getBean("b", A.class);
	System.out.println(c == d);
	ctx.close();
    }
}
