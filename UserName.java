/*
SRM 203
You are implementing the member registration system of an online dating site.
When a new member signs up, it is possible that she initially chooses the same
username as an existing member. The system must then inform the new member of
the conflict and suggest a variant of the chosen name with a number attached to
the end.

If an existing member is named "FunkyMonkey", for example, and a new member
wants the same username, the simplest suggestion the system can make is
"FunkyMonkey1". If there is already a member by that name, the system must
suggest "FunkyMonkey2", unless that variant is also taken. If all names from
"FunkyMonkey1" through "FunkyMonkey9" are taken as well as the original
"FunkyMonkey", the system moves on to consider "FunkyMonkey10", and so on. The
goal is to use the smallest possible number in the variant. Note that each
username consists of letters (the characters from 'a' to 'z' and from 'A' to
'Z') and numerals ('0' to '9').

You are given a String[], existingNames, containing all usernames that have
already been registered in the system. You are also given a single String,
newName, containing the username that a new member wants to use. In the
event of a conflict, this member will accept the suggestion offered by
your system in accordance with the principles above. Return a String
containing the username finally assigned to the new member.
*/

import java.util.*;

public class UserName {
    public static String newMember(String[] existingNames, String newName) {
        if (!Arrays.asList(existingNames).contains(newName))
            return newName;
        else {
            int count = 1;
            while (true) {
                String outputName = newName + (String) String.valueOf(count);
                if (!Arrays.asList(existingNames).contains(outputName)) {
                    return outputName;
                }
                count++;
            }
        }
    }

    public static void main (String[] args) {
        System.out.println(newMember(new String[] {"MasterOfDisaster", "DingBat", "Orpheus", "WolfMan", "MrKnowItAll"},"TygerTyger"));
        System.out.println(newMember(new String[] {"MasterOfDisaster", "TygerTyger1", "DingBat", "Orpheus", "TygerTyger", "WolfMan", "MrKnowItAll"}, "TygerTyger"));
        System.out.println(newMember(new String[] {"TygerTyger2000", "TygerTyger1", "MasterDisaster", "DingBat", "Orpheus", "WolfMan", "MrKnowItAll"}, "TygerTyger"));
        System.out.println(newMember(new String[] {"grokster2", "BrownEyedBoy", "Yoop", "BlueEyedGirl", "grokster", "Elemental", "NightShade", "Grokster1"}, "grokster"));
        System.out.println(newMember(new String[] {"Bart4", "Bart5", "Bart6", "Bart7", "Bart8", "Bart9", "Bart10", "Lisa", "Marge", "Homer", "Bart", "Bart1", "Bart2", "Bart3", "Bart11", "Bart12"}, "Bart"));
    }
}
