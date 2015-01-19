package hudson.plugins.sqlexecutor;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.model.AbstractProject;
import hudson.model.FreeStyleProject;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;

public class SqlExecutorBuilder extends Builder {

	private final String task;
//	private final static Logger logger = Logger.getLogger("SqlExecutorBuilder");
	
	@DataBoundConstructor
	public SqlExecutorBuilder(String task){
		this.task = task;
	}
	
	public String getTask() {
		return task;
	}

//	@Override
//	public boolean prebuild(AbstractBuild<?, ?> build, BuildListener listener) {
//		SqlExecutor.run("/home/piotr/workspace/Polsat/GuineaPigSVN", "xxx", "yyy");
//		
//		return super.prebuild(build, listener);
//	}
	
	@Extension
	public static class Descriptor extends BuildStepDescriptor<Builder>{

		@Override
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			return FreeStyleProject.class.isAssignableFrom(jobType);
		}

		@Override
		public String getDisplayName() {
			return "Execute sql files";
		}
		
		
	}
}
