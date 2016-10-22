import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Test {
	static Logger logger = LogManager.getLogger(Test.class.getName());

	public static void main(String[] args) {
		logger.debug("hellow world!");
	}
}
