
package graafinenlaskin;

import java.awt.Frame;
import org.assertj.swing.core.GenericTypeMatcher;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import org.junit.Test;

public class LaskinTest extends AssertJSwingJUnitTestCase {
    FrameFixture window;
    
    @Test
    public void tulosAlussaNolla() {
        window.textBox("tulos").requireText("0");           
    }         
    
    @Test
    public void luvunLisaysNolaanToimii() {
        window.textBox("syote").enterText("10");
        window.button("+").click();
        window.textBox("tulos").requireText("10");    
    }     
    
    // lisää testejä tänne
    
    @Override
    protected void onSetUp() {
        application(Main.class).start();

        window = findFrame(new GenericTypeMatcher<Frame>(Frame.class) {
            protected boolean isMatching(Frame frame) {
                return frame.isShowing();
            }
        }).using(robot());        
    }   
}
