import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class ThreadTest {

	public static void main(String[] args) {
		 System.out.println("start 1");
	        try{
	            ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(5);
	            executorService.scheduleWithFixedDelay(new Runnable() {
	                @Override
	                public void run() {
	                	System.out.println("thread start at " + System.currentTimeMillis());
	                }
	            }, 0,1, TimeUnit.SECONDS);
	       
	        }catch (Exception e){
	        	e.printStackTrace();
	        }

	}

}
