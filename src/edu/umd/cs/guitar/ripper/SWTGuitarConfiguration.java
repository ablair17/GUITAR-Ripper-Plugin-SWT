package edu.umd.cs.guitar.ripper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;

import edu.umd.cs.guitar.model.SWTApplication;
import edu.umd.cs.guitar.util.Util;

/**
 * Superclass of all SWT GUITAR configuration objects. This class collects
 * configuration options common to SWTRipper and SWTReplayer.
 * 
 * @author Gabe Gorelick
 * 
 */
public abstract class SWTGuitarConfiguration {

	@Option(name = "-?", usage = "print this help message", aliases = "--help")
	private boolean help;

	// GUITAR runtime parameters

	@Option(name = "-l", usage = "log file name ", aliases = "--log-file")
	private String logFile = Util.getTimeStamp() + ".log";

	@Option(name = "-i", usage = "initial waiting time for the GUI to get stablized (in milliseconds)", aliases = "--initial-wait")
	private int initialWaitTime = 0;

	@Option(name = "-at", usage = "maximum time to wait for the GUI to start (in milliseconds)", aliases = "--gui-start-timeout")
	private int guiStartTimeout = SWTApplication.DEFAULT_TIMEOUT;

	// Application Under Test parameters

	@Option(name = "-c", usage = "<REQUIRED> main class name for the Application Under Test ", aliases = "--main-class", required = true)
	private String mainClass = null;
	
	@Option(name = "-cf", usage = "Configure file for the ripper defining terminal, ignored components and ignored windows", aliases = "--configure-file")
    private String configFile = "configuration.xml";

	@Option(name = "-a", handler = StringArrayOptionHandler.class, usage = "arguments for the Application Under Test, separated by a colon (:) ", aliases = "--arguments")
    private String[] argumentList;

    @Option(name = "-u", handler = StringArrayOptionHandler.class, usage = "URLs for the Application Under Test, separated by a space ", aliases = "--urls")
    private String[] urlaList;
	
    private String urlList;
    
    // getters and setters
	
    //parser needs to be run before this is accessed
    public URL[] getUrls(){
    	URL[] alist = null;
    	if(urlaList != null){
    		alist = new URL[urlaList.length];

    		for (int i = 0; i < urlaList.length; i++){
    			try {
    				alist[i] = new URL(urlaList[i]);
    			} catch (MalformedURLException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    	return alist;
    }
    
	public void setHelp(boolean help) {
		this.help = help;
	}

	public boolean getHelp() {
		return help;
	}

	public void setLogFile(String logFile) {
		this.logFile = logFile;
	}

	public String getLogFile() {
		return logFile;
	}
	
	public void setGuiStartTimeout(int guiStartTimeout) {
		this.guiStartTimeout = guiStartTimeout;
	}
	
	public int getGuiStartTimeout() {
		return guiStartTimeout;
	}

	public void setInitialWaitTime(int initialWaitTime) {
		this.initialWaitTime = initialWaitTime;
	}

	public int getInitialWaitTime() {
		return initialWaitTime;
	}
	
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	public String getMainClass() {
		return mainClass;
	}
	
	public void setConfigFile(String configFile) {
		this.configFile = configFile;
	}

	public String getConfigFile() {
		return configFile;
	}
	
	public void setArgumentList(String[] argumentList) {
		this.argumentList = argumentList;
	}

	public String[] getArgumentList() {
		return argumentList;
	}

	// TODO use Args4j option handlers to make this array of URLs instead
	public void setUrlList(String urlList) {
		this.urlList = urlList;
	}
	
	public void setUrlArrayList(String[] arr){
		urlaList = arr;
	}

	public String getUrlList() {
		return urlList;
	}

}
