
public class HelloServiceImpl implements HelloService {
	private HelloDao dao = null;
	
	public HelloServiceImpl(HelloDao dao){
		this.dao = dao;
	}
	@Override
	public String hello() {
		return hello(null);
	}

	@Override
	public String hello(String name) {
		String defaultGreeting = dao.getGreeting();
		if (name != null && !name.equals("")){
			return defaultGreeting + ", " + name + "!";

		}
		return defaultGreeting + "!";
	}

}
