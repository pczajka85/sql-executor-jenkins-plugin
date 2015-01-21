package hudson.plugins.sqlexecutor;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;

import hudson.Extension;
import hudson.Launcher;
import hudson.WebAppMain;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.model.FreeStyleProject;
import hudson.plugins.utils.HibernateUtil;
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
	private final static Logger logger = Logger.getLogger(SqlExecutorBuilder.class.getName());
	
	private String username = "";
	private String password = "";
	
	private String hibernateUsername;
	private String hibernateDialect;
	private String hibernateDriverClass;
	private String hibernateUrl;
	private String hibernatePassword;
	
	@DataBoundConstructor
	public SqlExecutorBuilder(String task, final String password, final String username,
			final String hibernateUsername, final String hibernateDialect, final String hibernateDriverClass,
			final String hibernateUrl, final String hibernatePassword){
		this.task = task;
		this.username = username;
		this.password = password;
		this.hibernateUsername = hibernateUsername;
		this.hibernateDialect = hibernateDialect;
		this.hibernateDriverClass = hibernateDriverClass;
		this.hibernateUrl = hibernateUrl;
		this.hibernatePassword = hibernatePassword;
	}
	
	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher,
			BuildListener listener) throws InterruptedException, IOException {
		
		logger.info("SQL EXECUTOR Started");
		
		HibernateUtil.setUsername(this.hibernateUsername);
		HibernateUtil.setDialect(this.hibernateDialect);
		HibernateUtil.setDriverClass(this.hibernateDriverClass);
		HibernateUtil.setUrl(this.hibernateUrl);
		HibernateUtil.setPassword(this.hibernatePassword);
		
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
				
	public String getHibernateUsername() {
		return hibernateUsername;
	}

	public String getHibernateDialect() {
		return hibernateDialect;
	}

	public String getHibernateDriverClass() {
		return hibernateDriverClass;
	}

	public String getHibernateUrl() {
		return hibernateUrl;
	}

	public String getHibernatePassword() {
		return hibernatePassword;
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
