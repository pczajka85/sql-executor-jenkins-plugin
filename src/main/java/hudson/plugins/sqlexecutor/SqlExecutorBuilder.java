package hudson.plugins.sqlexecutor;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

/**
 * 
 * @author Piotr Czajka
 *
 */
public class SqlExecutorBuilder extends Builder {

	private final String task;

	//TODO let it be jenkins console output's logger
	private final static Logger logger = Logger.getLogger("SqlExecutorBuilder");
	
	private String username = "";
	private String password = "";
	
	@DataBoundConstructor
	public SqlExecutorBuilder(String task, final String password, final String username){
		this.task = task;
		this.username = username;
		this.password = password;
	}
	
	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws InterruptedException, IOException {
		
		logger.info("SQL EXECUTOR Started");
		
//		for(Map.Entry<String, String> entry : build.getEnvironment(listener).entrySet()){
//			logger.info("VAR="+entry.getKey()+" => "+entry.getValue());
//		}
		
		SqlExecutor.run(
				build.getEnvironment(listener).get("SVN_URL"), 
				build.getEnvironment(listener).get("WORKSPACE"),
				this.username, 
				this.password);
		return  true;
	}
	
	public String getTask() {
		return task;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getUsername() {
		return username;
	}
				
	@Extension
	public static class Descriptor extends BuildStepDescriptor<Builder>{
		
		@SuppressWarnings("rawtypes")
		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return FreeStyleProject.class.isAssignableFrom(jobType);
		}

		@Override
		public String getDisplayName() {
			return "Execute sql files";
		}
		
		@Override
		public boolean configure(StaplerRequest req, JSONObject json)
				throws hudson.model.Descriptor.FormException {
			
			return super.configure(req, json);
		}
	}
}
