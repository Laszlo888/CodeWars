/*
    Kate constantly finds herself in some kind of a maze. Help her to find a way out!.
    For a given maze and Kate's position find if there is a way out. Your function should return True or False.
    Each maze is defined as a list of strings, where each char stays for a single maze "cell".
    ' ' (space) can be stepped on, '#' means wall and 'k' stays for Kate's starting position.
    Note that the maze may not always be square or even rectangular.
    Kate can move left, up, right or down only.
    There should be only one Kate in a maze. In any other case you have to throw an exception.

    Example input:
    ['# ##',
     '# k#',
     '####']

    Example output: True

    Example input:
    ['####'.
     '# k#',
     '####']

    Example output: False
*/

import java.util.*;

public class SimpleMaze2 {
    public static void main(String[] args) {
        System.out.println(hasExit(new String[]{

                "###########",
                "#k        #",
                "#########"
        }));
    }

    public static boolean hasExit(String[] maze) {
        // searching and counting Kate
        int kateCounter = 0;
        int[] katePosition = new int[2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length(); j++) {
                if (maze[i].charAt(j) == 'k') {
                    kateCounter++;
                    if (kateCounter > 1) {
                        throw new RuntimeException();
                    } else {
                        katePosition[0] = i;
                        katePosition[1] = j;
                    }
                }
            }
        }
        if (kateCounter == 0) {
            throw new RuntimeException();
        }
        if (maze.length < 3) {
            return true;
        }

        // check maze is rectangle or not, if not make it a rectangle
        int maxLengthRow = 0;
        for (int i = 0; i < maze.length; i++) {
            if (maze[i].length() > maxLengthRow) {
                maxLengthRow = maze[i].length();
            }
        }
        for (int i = 0; i < maze.length; i++) {
            if (maze[i].length() < maxLengthRow) {
                for (int j = 1; j <= (maxLengthRow - maze[i].length()); j++) {
                    maze[i] += " ";
                }
            }
        }

        // if we have minimum 3 rows, search possible exits
        boolean hasBordersExit = false;
        List<int[]> possibleExits = new ArrayList<>();
        for (int i = 0; i < maze.length; i++) {
            if (i == 0 || i == maze.length - 1) {
                for (int j = 0; j < maze[i].length(); j++) {
                    if (maze[i].charAt(j) != '#') {
                        hasBordersExit = true;
                        possibleExits.add(new int[]{i, j});
                    }
                }
            } else {
                if (maze[i].charAt(0) != '#') {
                    hasBordersExit = true;
                    possibleExits.add(new int[]{i, 0});
                }
                if (maze[i].charAt(maze[i].length() - 1) != '#') {
                    hasBordersExit = true;
                    possibleExits.add(new int[]{i, maze[i].length() - 1});
                }
            }
        }

        if (!hasBordersExit) {
            return false;
        } else {
            Map<Integer, List<String>> junctions = new HashMap<>();
            int[] tempPosition = new int[2];
            tempPosition[0] = katePosition[0];
            tempPosition[1] = katePosition[1];
            StringBuilder tempSb = new StringBuilder();
            tempSb.append(tempPosition[0]).append(tempPosition[1]);
            int tempPositionForJunctionMap = Integer.parseInt(tempSb.toString());
            StringBuilder junctionDirectionChoose = new StringBuilder();
            char left;
            char top;
            char bottom;
            char right;
            StringBuilder formerStep = new StringBuilder();
            //remove k
            StringBuilder removeK = new StringBuilder(maze[tempPosition[0]]);
            removeK.replace(tempPosition[1], tempPosition[1] + 1, " ");
            maze[tempPosition[0]] = removeK.toString();

            if (kateCounter == 1) {
                do {
                    // have we wall at left? from current position
                    left = maze[tempPosition[0]].charAt(tempPosition[1] - 1);
                    top = maze[tempPosition[0] - 1].charAt(tempPosition[1]);
                    bottom = maze[tempPosition[0] + 1].charAt(tempPosition[1]);
                    right = maze[tempPosition[0]].charAt(tempPosition[1] + 1);

                    // no way can go
                    if (left == '#' && top == '#' && bottom == '#' && right == '#') {
                        return false;
                    }

                    // one way can go
                    else if (left == '#' && top == '#' && bottom == '#' && right != '#') {
                        if (right == 'S') {
                            return false;
                        } else {
                            stepRight(tempPosition, formerStep, maze, false, true);
                        }
                    } else if (left == '#' && top == '#' && bottom != '#' && right == '#') {
                        if (bottom == 'S') {
                            return false;
                        } else {
                            stepDown(tempPosition, formerStep, maze, false, true);
                        }
                    } else if (left == '#' && top != '#' && bottom == '#' && right == '#') {
                        if (top == 'S') {
                            return false;
                        } else {
                            stepTop(tempPosition, formerStep, maze, false, true);
                        }
                    } else if (left != '#' && top == '#' && bottom == '#' && right == '#') {
                        if (left == 'S') {
                            return false;
                        } else {
                            stepLeft(tempPosition, formerStep, maze, false, true);
                        }
                    }

                    // 2 ways can go
                    else if (left == '#' && top == '#' && bottom != '#' && right != '#') {
                        if (formerStep.toString().equals("left") || formerStep.toString().equals("")) {
                            if (bottom != 'S') {
                                stepDown(tempPosition, formerStep, maze, false, false);
                            } else if (right != 'S') {
                                stepRight(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        } else {
                            if (right != 'S') {
                                stepRight(tempPosition, formerStep, maze, false, false);
                            } else if (bottom != 'S') {
                                stepDown(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        }
                    } else if (left == '#' && top != '#' && bottom == '#' && right != '#') {
                        if (formerStep.toString().equals("down") || formerStep.toString().equals("")) {
                            if (right != 'S') {
                                stepRight(tempPosition, formerStep, maze, false, false);
                            } else if (top != 'S') {
                                stepTop(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        } else {
                            if (top != 'S') {
                                stepTop(tempPosition, formerStep, maze, false, false);
                            } else if (right != 'S') {
                                stepRight(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        }
                    } else if (left != '#' && top == '#' && bottom == '#' && right != '#') {
                        if (formerStep.toString().equals("left") || formerStep.toString().equals("")) {
                            if (left != 'S') {
                                stepLeft(tempPosition, formerStep, maze, false, false);
                            } else if (right != 'S') {
                                stepRight(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        } else {
                            if (right != 'S') {
                                stepRight(tempPosition, formerStep, maze, false, false);
                            } else if (left != 'S') {
                                stepLeft(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        }
                    } else if (left == '#' && top != '#' && bottom != '#' && right == '#') {
                        if (formerStep.toString().equals("down") || formerStep.toString().equals("")) {
                            if (bottom != 'S') {
                                stepDown(tempPosition, formerStep, maze, false, false);
                            } else if (top != 'S') {
                                stepTop(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        } else {
                            if (top != 'S') {
                                stepTop(tempPosition, formerStep, maze, false, false);
                            } else if (bottom != 'S') {
                                stepDown(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        }
                    } else if (left != '#' && top != '#' && bottom == '#' && right == '#') {
                        if (formerStep.toString().equals("right") || formerStep.toString().equals("")) {
                            if (top != 'S') {
                                stepTop(tempPosition, formerStep, maze, false, false);
                            } else if (left != 'S') {
                                stepLeft(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        } else {
                            if (left != 'S') {
                                stepLeft(tempPosition, formerStep, maze, false, false);
                            } else if (top != 'S') {
                                stepTop(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        }
                    } else if (left != '#' && top == '#' && bottom != '#' && right == '#') {
                        if (formerStep.toString().equals("top") || formerStep.toString().equals("")) {
                            if (left != 'S') {
                                stepLeft(tempPosition, formerStep, maze, false, false);
                            } else if (bottom != 'S') {
                                stepDown(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        } else {
                            if (bottom != 'S') {
                                stepDown(tempPosition, formerStep, maze, false, false);
                            } else if (left != 'S') {
                                stepLeft(tempPosition, formerStep, maze, false, false);
                            } else {
                                return false;
                            }
                        }
                    }

                    // 3 or 4 ways can go (junctions)
                    else {
                        if (atJunction(left, right, top, bottom, junctionDirectionChoose, tempPosition, formerStep, maze, junctions, tempPositionForJunctionMap) == false) {
                            return false;
                        }
                    }
                    tempSb.delete(0, tempSb.length());
                    tempSb.append(tempPosition[0]).append(tempPosition[1]);
                    tempPositionForJunctionMap = Integer.parseInt(tempSb.toString());
                    junctionDirectionChoose.delete(0, junctionDirectionChoose.length());
                } while (!exitChecker(possibleExits, tempPosition));
                if (exitChecker(possibleExits, tempPosition)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public static void stepRight(int[] tempPosition, StringBuilder formerStep, String[] maze, boolean isJunction, boolean isDeadEnd) {
        if (isJunction == false) {
            StringBuilder temp = new StringBuilder(maze[tempPosition[0]]);
            if (isDeadEnd == true) {
                temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
            } else {
                if (maze[tempPosition[0]].charAt(tempPosition[1]) != 's') {
                    temp.replace(tempPosition[1], tempPosition[1] + 1, "s");
                } else if (maze[tempPosition[0]].charAt(tempPosition[1]) == 's') {
                    temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
                }
            }
            maze[tempPosition[0]] = temp.toString();
        }
        tempPosition[1] = tempPosition[1] + 1;
        formerStep.delete(0, formerStep.length());
        formerStep.append("right");
    }

    public static void stepLeft(int[] tempPosition, StringBuilder formerStep, String[] maze, boolean isJunction, boolean isDeadEnd) {
        if (isJunction == false) {
            StringBuilder temp = new StringBuilder(maze[tempPosition[0]]);
            if (isDeadEnd == true) {
                temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
            } else {
                if (maze[tempPosition[0]].charAt(tempPosition[1]) != 's') {
                    temp.replace(tempPosition[1], tempPosition[1] + 1, "s");
                } else if (maze[tempPosition[0]].charAt(tempPosition[1]) == 's') {
                    temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
                }
            }
            maze[tempPosition[0]] = temp.toString();
        }
        tempPosition[1] = tempPosition[1] - 1;
        formerStep.delete(0, formerStep.length());
        formerStep.append("left");
    }

    public static void stepTop(int[] tempPosition, StringBuilder formerStep, String[] maze, boolean isJunction, boolean isDeadEnd) {
        if (isJunction == false) {
            StringBuilder temp = new StringBuilder(maze[tempPosition[0]]);
            if (isDeadEnd == true) {
                temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
            } else {
                if (maze[tempPosition[0]].charAt(tempPosition[1]) != 's') {
                    temp.replace(tempPosition[1], tempPosition[1] + 1, "s");
                } else if (maze[tempPosition[0]].charAt(tempPosition[1]) == 's') {
                    temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
                }
            }
            maze[tempPosition[0]] = temp.toString();
        }
        tempPosition[0] = tempPosition[0] - 1;
        formerStep.delete(0, formerStep.length());
        formerStep.append("top");
    }

    public static void stepDown(int[] tempPosition, StringBuilder formerStep, String[] maze, boolean isJunction, boolean isDeadEnd) {
        if (isJunction == false) {
            StringBuilder temp = new StringBuilder(maze[tempPosition[0]]);
            if (maze[tempPosition[0]].charAt(tempPosition[1]) != 's') {
                temp.replace(tempPosition[1], tempPosition[1] + 1, "s");
            } else if (maze[tempPosition[0]].charAt(tempPosition[1]) == 's') {
                temp.replace(tempPosition[1], tempPosition[1] + 1, "S");
            }
            maze[tempPosition[0]] = temp.toString();
        }
        tempPosition[0] = tempPosition[0] + 1;
        formerStep.delete(0, formerStep.length());
        formerStep.append("down");
    }

    public static boolean atJunction(char left, char right, char top, char bottom,
                                     StringBuilder junctionDirectionChoose, int[] tempPosition, StringBuilder formerStep, String[] maze,
                                     Map<Integer, List<String>> junctions, int tempPositionForJunctionMap) {
        if (junctions.get(tempPositionForJunctionMap) == null) {
            if (left == '#') {
                junctions.put(tempPositionForJunctionMap, new ArrayList<>());
                if (right != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("right");
                }
                if (top != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("top");
                }
                if (bottom != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("down");
                }
            } else if (top == '#') {
                junctions.put(tempPositionForJunctionMap, new ArrayList<>());
                if (right != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("right");
                }
                if (left != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("left");
                }
                if (bottom != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("down");
                }
            } else if (bottom == '#') {
                junctions.put(tempPositionForJunctionMap, new ArrayList<>());
                if (right != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("right");
                }
                if (top != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("top");
                }
                if (left != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("left");
                }
            } else if (right == '#') {
                junctions.put(tempPositionForJunctionMap, new ArrayList<>());
                if (left != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("left");
                }
                if (top != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("top");
                }
                if (bottom != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("down");
                }
            } else {
                junctions.put(tempPositionForJunctionMap, new ArrayList<>());
                if (right != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("right");
                }
                if (top != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("top");
                }
                if (bottom != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("down");
                }
                if (left != 'S') {
                    junctions.get(tempPositionForJunctionMap).add("left");
                }
            }

            // don't move backwards and if possible follow same line
            List<String> freeDirections = new ArrayList<>(junctions.get(tempPositionForJunctionMap));
            boolean isGoForward = false;
            switch (formerStep.toString()) {
                case "left":
                    freeDirections.remove("right");
                    if (freeDirections.contains("left")) {
                        junctions.get(tempPositionForJunctionMap).remove("left");
                        isGoForward = true;
                        stepLeft(tempPosition, formerStep, maze, true, false);
                    }
                    break;
                case "right":
                    freeDirections.remove("left");
                    if (freeDirections.contains("right")) {
                        junctions.get(tempPositionForJunctionMap).remove("right");
                        isGoForward = true;
                        stepRight(tempPosition, formerStep, maze, true, false);
                    }
                    break;
                case "top":
                    freeDirections.remove("down");
                    if (freeDirections.contains("top")) {
                        junctions.get(tempPositionForJunctionMap).remove("top");
                        isGoForward = true;
                        stepTop(tempPosition, formerStep, maze, true, false);
                    }
                    break;
                case "down":
                    freeDirections.remove("top");
                    if (freeDirections.contains("down")) {
                        junctions.get(tempPositionForJunctionMap).remove("down");
                        isGoForward = true;
                        stepDown(tempPosition, formerStep, maze, true, false);
                    }
                    break;
            }
            if (isGoForward == false) {
                junctionDirectionChoose.append(randomChooseAtJunctions(freeDirections));
                if (junctionDirectionChoose.toString().equals("right")) {
                    junctions.get(tempPositionForJunctionMap).remove("right");
                    stepRight(tempPosition, formerStep, maze, true, false);
                } else if (junctionDirectionChoose.toString().equals("top")) {
                    junctions.get(tempPositionForJunctionMap).remove("top");
                    stepTop(tempPosition, formerStep, maze, true, false);
                } else if (junctionDirectionChoose.toString().equals("down")) {
                    junctions.get(tempPositionForJunctionMap).remove("down");
                    stepDown(tempPosition, formerStep, maze, true, false);
                } else {
                    junctions.get(tempPositionForJunctionMap).remove("left");
                    stepLeft(tempPosition, formerStep, maze, true, false);
                }
            }
            return true;
        } else if (junctions.get(tempPositionForJunctionMap).size() == 0) {
            boolean isMoreChance = false;
            for (Map.Entry<Integer, List<String>> entry : junctions.entrySet()) {
                if (entry.getValue().size() > 0) {
                    StringBuilder temp = new StringBuilder(String.valueOf(entry.getKey()));
                    tempPosition[0] = Integer.parseInt(String.valueOf(temp.toString().charAt(0)));
                    tempPosition[1] = Integer.parseInt(String.valueOf(temp.toString().charAt(1)));
                    isMoreChance = true;
                    break;
                }
            }
            if (isMoreChance == true) {
                return true;
            } else {
                return false;
            }
        } else {

            if (top == 'S') {
                if (junctions.get(tempPositionForJunctionMap).contains("top")) {
                    junctions.get(tempPositionForJunctionMap).remove("top");
                }
            }
            if (right == 'S') {
                if (junctions.get(tempPositionForJunctionMap).contains("right")) {
                    junctions.get(tempPositionForJunctionMap).remove("right");
                }
            }
            if (bottom == 'S') {
                if (junctions.get(tempPositionForJunctionMap).contains("down")) {
                    junctions.get(tempPositionForJunctionMap).remove("down");
                }
            }
            if (left == 'S') {
                if (junctions.get(tempPositionForJunctionMap).contains("left")) {
                    junctions.get(tempPositionForJunctionMap).remove("left");
                }
            }
            if (junctions.get(tempPositionForJunctionMap).size() == 0) {
                boolean isMoreChance = false;
                for (Map.Entry<Integer, List<String>> entry : junctions.entrySet()) {
                    if (entry.getValue().size() > 0) {
                        StringBuilder temp = new StringBuilder(String.valueOf(entry.getKey()));
                        tempPosition[0] = Integer.parseInt(String.valueOf(temp.toString().charAt(0)));
                        tempPosition[1] = Integer.parseInt(String.valueOf(temp.toString().charAt(1)));
                        isMoreChance = true;
                        break;
                    }
                }
                if (isMoreChance == true) {
                    return true;
                } else {
                    return false;
                }
            } else {

                List<String> sFreeDirections = new ArrayList<>();
                if (top == ' ') {
                    if (junctions.get(tempPositionForJunctionMap).contains("top")) {
                        sFreeDirections.add("top");
                    }
                }
                if (right == ' ') {
                    if (junctions.get(tempPositionForJunctionMap).contains("right")) {
                        sFreeDirections.add("right");
                    }
                }
                if (bottom == ' ') {
                    if (junctions.get(tempPositionForJunctionMap).contains("down")) {
                        sFreeDirections.add("down");
                    }
                }
                if (left == ' ') {
                    if (junctions.get(tempPositionForJunctionMap).contains("left")) {
                        sFreeDirections.add("left");
                    }
                }

                if (sFreeDirections.size() == 1) {
                    if (junctions.get(tempPositionForJunctionMap).contains(sFreeDirections.get(0))) {
                        onePossibleDirection(sFreeDirections, junctions, tempPositionForJunctionMap, tempPosition, formerStep, maze);
                    } else {
                        sFreeDirections.clear();
                        if (top == 's') {
                            if (junctions.get(tempPositionForJunctionMap).contains("top")) {
                                sFreeDirections.add("top");
                            }
                        }
                        if (right == 's') {
                            if (junctions.get(tempPositionForJunctionMap).contains("right")) {
                                sFreeDirections.add("right");
                            }
                        }
                        if (bottom == 's') {
                            if (junctions.get(tempPositionForJunctionMap).contains("down")) {
                                sFreeDirections.add("down");
                            }
                        }
                        if (left == 's') {
                            if (junctions.get(tempPositionForJunctionMap).contains("left")) {
                                sFreeDirections.add("left");
                            }
                        }
                        if (sFreeDirections.size() == 1) {
                            onePossibleDirection(sFreeDirections, junctions, tempPositionForJunctionMap, tempPosition, formerStep, maze);
                        } else if (sFreeDirections.size() > 1) {
                            StringBuilder tp = new StringBuilder(randomChooseAtJunctions(sFreeDirections));
                            switch (tp.toString()) {
                                case "left":
                                    junctions.get(tempPositionForJunctionMap).remove("left");
                                    stepLeft(tempPosition, formerStep, maze, true, false);
                                    break;
                                case "right":
                                    junctions.get(tempPositionForJunctionMap).remove("right");
                                    stepRight(tempPosition, formerStep, maze, true, false);
                                    break;
                                case "top":
                                    junctions.get(tempPositionForJunctionMap).remove("top");
                                    stepTop(tempPosition, formerStep, maze, true, false);
                                    break;
                                case "down":
                                    junctions.get(tempPositionForJunctionMap).remove("down");
                                    stepDown(tempPosition, formerStep, maze, true, false);
                                    break;
                            }
                        } else {
                            return false;
                        }
                    }
                } else if (sFreeDirections.size() > 1) {
                    boolean isGoForward = false;
                    switch (formerStep.toString()) {
                        case "left":
                            if (junctions.get(tempPositionForJunctionMap).contains("left") && sFreeDirections.contains("left")) {
                                isGoForward = true;
                                junctions.get(tempPositionForJunctionMap).remove("left");
                                stepLeft(tempPosition, formerStep, maze, true, false);
                            }
                            sFreeDirections.remove("right");
                            break;
                        case "right":
                            if (junctions.get(tempPositionForJunctionMap).contains("right") && sFreeDirections.contains("right")) {
                                isGoForward = true;
                                junctions.get(tempPositionForJunctionMap).remove("right");
                                stepRight(tempPosition, formerStep, maze, true, false);
                            }
                            sFreeDirections.remove("left");
                            break;
                        case "top":
                            if (junctions.get(tempPositionForJunctionMap).contains("top") && sFreeDirections.contains("top")) {
                                isGoForward = true;
                                junctions.get(tempPositionForJunctionMap).remove("top");
                                stepTop(tempPosition, formerStep, maze, true, false);
                            }
                            sFreeDirections.remove("down");
                            break;
                        case "down":
                            if (junctions.get(tempPositionForJunctionMap).contains("down") && sFreeDirections.contains("down")) {
                                isGoForward = true;
                                junctions.get(tempPositionForJunctionMap).remove("down");
                                stepDown(tempPosition, formerStep, maze, true, false);
                            }
                            sFreeDirections.remove("top");
                            break;
                    }

                    if (isGoForward == false) {
                        if (sFreeDirections.size() == 1) {
                            onePossibleDirection(sFreeDirections, junctions, tempPositionForJunctionMap, tempPosition, formerStep, maze);
                        } else if (sFreeDirections.size() > 1) {
                            junctionDirectionChoose.append(randomChooseAtJunctions(sFreeDirections));
                            if (junctionDirectionChoose.toString().equals("right")) {
                                junctions.get(tempPositionForJunctionMap).remove("right");
                                stepRight(tempPosition, formerStep, maze, true, false);
                            } else if (junctionDirectionChoose.toString().equals("top")) {
                                junctions.get(tempPositionForJunctionMap).remove("top");
                                stepTop(tempPosition, formerStep, maze, true, false);
                            } else if (junctionDirectionChoose.toString().equals("down")) {
                                junctions.get(tempPositionForJunctionMap).remove("down");
                                stepDown(tempPosition, formerStep, maze, true, false);
                            } else {
                                junctions.get(tempPositionForJunctionMap).remove("left");
                                stepLeft(tempPosition, formerStep, maze, true, false);
                            }
                        }
                    }
                } else if (sFreeDirections.size() == 0) {
                    if (top == 's') {
                        if (junctions.get(tempPositionForJunctionMap).contains("top")) {
                            sFreeDirections.add("top");
                        }
                    }
                    if (right == 's') {
                        if (junctions.get(tempPositionForJunctionMap).contains("right")) {
                            sFreeDirections.add("right");
                        }
                    }
                    if (bottom == 's') {
                        if (junctions.get(tempPositionForJunctionMap).contains("down")) {
                            sFreeDirections.add("down");
                        }
                    }
                    if (left == 's') {
                        if (junctions.get(tempPositionForJunctionMap).contains("left")) {
                            sFreeDirections.add("left");
                        }
                    }
                    if (sFreeDirections.size() == 1) {
                        onePossibleDirection(sFreeDirections, junctions, tempPositionForJunctionMap, tempPosition, formerStep, maze);
                    } else if (sFreeDirections.size() > 1) {
                        switch (formerStep.toString()) {
                            case "left":
                                sFreeDirections.remove("right");
                                break;
                            case "right":
                                sFreeDirections.remove("left");
                                break;
                            case "top":
                                sFreeDirections.remove("down");
                                break;
                            case "down":
                                sFreeDirections.remove("top");
                                break;
                        }
                        if (sFreeDirections.size() == 1) {
                            onePossibleDirection(sFreeDirections, junctions, tempPositionForJunctionMap, tempPosition, formerStep, maze);
                        } else {
                            junctionDirectionChoose.append(randomChooseAtJunctions(sFreeDirections));
                            if (junctionDirectionChoose.toString().equals("right")) {
                                junctions.get(tempPositionForJunctionMap).remove("right");
                                stepRight(tempPosition, formerStep, maze, true, false);
                            } else if (junctionDirectionChoose.toString().equals("top")) {
                                junctions.get(tempPositionForJunctionMap).remove("top");
                                stepTop(tempPosition, formerStep, maze, true, false);
                            } else if (junctionDirectionChoose.toString().equals("down")) {
                                junctions.get(tempPositionForJunctionMap).remove("down");
                                stepDown(tempPosition, formerStep, maze, true, false);
                            } else {
                                junctions.get(tempPositionForJunctionMap).remove("left");
                                stepLeft(tempPosition, formerStep, maze, true, false);
                            }
                        }
                    } else if (sFreeDirections.size() == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void onePossibleDirection(List<String> sFreeDirections, Map<Integer, List<String>> junctions, int tempPositionForJunctionMap, int[] tempPosition, StringBuilder
            formerStep, String[] maze) {
        if (sFreeDirections.get(0).equals("right")) {
            junctions.get(tempPositionForJunctionMap).remove("right");
            stepRight(tempPosition, formerStep, maze, true, false);
        } else if (sFreeDirections.get(0).equals("top")) {
            junctions.get(tempPositionForJunctionMap).remove("top");
            stepTop(tempPosition, formerStep, maze, true, false);
        } else if (sFreeDirections.get(0).equals("down")) {
            junctions.get(tempPositionForJunctionMap).remove("down");
            stepDown(tempPosition, formerStep, maze, true, false);
        } else {
            junctions.get(tempPositionForJunctionMap).remove("left");
            stepLeft(tempPosition, formerStep, maze, true, false);
        }
    }

    public static String randomChooseAtJunctions(List<String> availableDirections) {
        Random rand = new Random();
        return availableDirections.get(rand.nextInt(availableDirections.size()));
    }

    public static boolean exitChecker(List<int[]> possibleExits, int[] tempPosition) {
        for (int[] possibleExit : possibleExits) {
            if (Arrays.equals(possibleExit, tempPosition)) {
                return true;
            }
        }
        return false;
    }
}
