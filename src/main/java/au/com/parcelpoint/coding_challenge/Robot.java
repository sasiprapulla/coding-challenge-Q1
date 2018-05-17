package au.com.parcelpoint.coding_challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Robot {
    
    private static final int MAX_COLUMN_SIZE = 10;
    private static final int ROBOT_DEFAULT_POSITION = -1;

    public Map<String, List<String>> simulateArm(final String input) {
        
        Map<String, List<String>> output = new HashMap<>();
        String columns = "ABCDE";
        int robotPosition = ROBOT_DEFAULT_POSITION;
        
        //initializing columns
        for(int i = 0, len = columns.length(); i < len; i++){
            output.put(Character.toString(columns.charAt(i)), new ArrayList<>(MAX_COLUMN_SIZE));
        }
        
        if(input != null && !input.equals("")){
        
            String[] loadDrops = input.split("}");
            
            for(int i = 0, len = loadDrops.length; i < len; i++){
                String  currentLoad = loadDrops[i];
                boolean isCommandsCompleted = false;
                int j = 0;
                
                while(!isCommandsCompleted){
                    char currentCommand = currentLoad.charAt(j);
                    switch(currentCommand){
                        case '{':
                            isCommandsCompleted = true;
                            String item = currentLoad.substring(j + 1);
                            if(robotPosition != ROBOT_DEFAULT_POSITION) {
                                List currentList = output.get(Character.toString(columns.charAt(robotPosition)));
                                if(currentList.size() < MAX_COLUMN_SIZE){ //If column is not full and Robot is not at default position
                                    currentList.add(item);
                                }
                                if (robotPosition < columns.length() - 1) {
                                    robotPosition++;
                                }
                            }
                            
                            break;
                        case 'F':
                            if (robotPosition < columns.length() - 1) {
                                robotPosition++;
                            }
                            break;
                        case 'R':
                            robotPosition = ROBOT_DEFAULT_POSITION;
                            break;
                    }
                    j++;
                }
            }        
        }
        
        return output;
    }
    
    //Added to test
    public static void main(String args[]){
        Robot robot = new Robot();
        System.out.print(robot.simulateArm("FD{foo}FFD{bar}RFFD{Foo}RFFD{World}D{}DA{hello}"));
    }
   
}
