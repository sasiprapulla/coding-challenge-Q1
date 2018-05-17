package au.com.parcelpoint.coding_challenge;

import java.util.List;
import java.util.Map;
import org.junit.*;

/**
 * This is JUnit Test case to test Robot class.
 * Instructions to run this : install maven in the system and use the command "mvn test"

*/
public class RobotTest {
    
    Robot robot = new Robot();
    @Test
    public void simulateArmWithValidInput(){
        
        Map<String, List<String>> output = robot.simulateArm("FD{foo}FFD{bar}RFFD{Foo}RFFD{World}D{}DA{hello}");
        String expected = "{A=[foo], B=[Foo, World], C=[], D=[bar, hello], E=[]}";
        Assert.assertEquals(expected, output.toString());
    }
    
    @Test
    public void itemDroppedInDefaultPos(){
        Map<String, List<String>> output = robot.simulateArm("D{foo}");
        String expected = "{A=[], B=[], C=[], D=[], E=[]}";
        Assert.assertEquals("Item droped in default position should not be added",expected, output.toString());
    }
    
    @Test
    public void simulateArmWithInValidCommands(){
        Map<String, List<String>> output = robot.simulateArm("SSSFD{foo}");
        String expected = "{A=[foo], B=[], C=[], D=[], E=[]}";
        Assert.assertEquals("Invalid commands should be ignored",expected, output.toString());
    }
    
    @Test
    public void checkFCommand(){
        Map<String, List<String>> output = robot.simulateArm("FD{foo}FD{bar}");
        String expected = "{A=[foo], B=[], C=[bar], D=[], E=[]}";
        Assert.assertEquals("F command should move the arm forward",expected, output.toString());
    }
    
    @Test
    public void checkDCommand(){
        Map<String, List<String>> output = robot.simulateArm("FD{bar}D{hello}D{foo}");
        String expected = "{A=[bar], B=[hello], C=[foo], D=[], E=[]}";
        Assert.assertEquals("D command should drop the load and move forward",expected, output.toString());
    }
    
    @Test
    public void checkRCommand(){
        Map<String, List<String>> output = robot.simulateArm("FFFRFD{bar}");
        String expected = "{A=[bar], B=[], C=[], D=[], E=[]}";
        Assert.assertEquals("R command should reset the arm to default position",expected, output.toString());
    }
    
    @Test
    public void checkDropLoadWhenColumnIsFull(){
        Map<String, List<String>> output = robot.simulateArm("FFFFFD{load1}D{load2}D{load3}D{load4}D{load5}D{load6}D{load7}D{load8}D{load9}D{load10}D{load11}");
        String expected = "{A=[], B=[], C=[], D=[], E=[load1, load2, load3, load4, load5, load6, load7, load8, load9, load10]}";
        Assert.assertEquals("Coloumn should allow 10 loads only",expected, output.toString());
    }
    
    @Test
    public void checkArmCannotMoveBeyondLastColumn(){
        Map<String, List<String>> output = robot.simulateArm("FFFFFFFFFFD{bar}");
        String expected = "{A=[], B=[], C=[], D=[], E=[bar]}";
        Assert.assertEquals("Arm should not move beyond last column E",expected, output.toString());
    }
    
    @Test
    public void checkCommandsAreCaseSensitive(){
        Map<String, List<String>> output = robot.simulateArm("fd{bar}");
        String expected = "{A=[], B=[], C=[], D=[], E=[]}";
        Assert.assertEquals("CommandsAreCaseSensitive",expected, output.toString());
    }

}
