package SwtJxBrowserOSX;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import com.teamdev.jxbrowser.engine.EngineOptions.Builder;
import com.teamdev.jxbrowser.engine.RenderingMode;
import com.teamdev.jxbrowser.view.swt.BrowserView;

public class App {
    public static void main(String[] arguments) {
    	boolean renderHW = !"true".equals(System.getProperty("offscreen"));
    	String jxlicense = System.getProperty("jxlicense");
    	
    	Builder builder = EngineOptions.newBuilder(renderHW ? RenderingMode.HARDWARE_ACCELERATED : RenderingMode.OFF_SCREEN);
		if (jxlicense != null) {
			builder.licenseKey(jxlicense);
		}
    	Engine browserContext = Engine.newInstance(builder.build());
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setLayout(new FillLayout());
         
        Composite composite = new Composite(shell, SWT.EMBEDDED | SWT.NO_BACKGROUND);
        composite.setLayout(new FillLayout());
        BrowserView browserView = BrowserView.newInstance(composite, browserContext.newBrowser());
         
        browserView.getBrowser().navigation().loadUrl("https://google.com");
         
        shell.open();
         
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
