

#  Neko-bot  User Guide
By: `Chan Jian Hao` Since: `September 2020` License: `MIT`

![logo](https://i.imgur.com/Z2KOn6Y.png)


## 1. Introduction
Neko-bot is a simple desktop application used for managing and tracking tasks (e.g., Todos, Events, Deadlines).

It allows you to add, remove, and mark tasks as done whenever you want to, and it features a persistent storage capability where you can always view your existing task list each time you start the program.

Finally, it allows you to search for tasks by their names and find events/deadlines based on their dates.

## 2. Quick Start with Intellij
Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project dialog first)
1. Set up the correct JDK version, as follows:
   1. Click `Configure` > `Structure for New Projects` and then `Project Settings` > `Project` > `Project SDK`
   1. If JDK 11 is listed in the drop-down, select it. If it is not, click `New...` and select the directory where you installed JDK 11
   1. Click `OK`
1. Import the project into Intellij as follows:
   1. Click `Open or Import`.
   1. Select the project directory, and click `OK`
   1. If there are any further prompts, accept the defaults.
1. After the importing is complete, locate the `src/main/java/duke/Duke.java` file, right-click it, and choose `Run duke.Duke.main()`. If the setup is correct, you should see something like the below:

    ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░ ░░░░░░░░░░▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄▄░░░░░░░░░ ░░░░░░░░▄▀░░░░░░░░░░░░▄░░░░░░░▀▄░░░░░░░ ░░░░░░░░█░░▄░░░░▄░░░░░░░░░░░░░░█░░░░░░░ ░░░░░░░░█░░░░░░░░░░░░▄█▄▄░░▄░░░█░▄▄▄░░░ ░▄▄▄▄▄░░█░░░░░░▀░░░░▀█░░▀▄░░░░░█▀▀░██░░ ░██▄▀██▄█░░░▄░░░░░░░██░░░░▀▀▀▀▀░░░░██░░ ░░▀██▄▀██░░░░░░░░▀░██▀░░░░░░░░░░░░░▀██░ ░░░░▀████░▀░░░░▄░░░██░░░▄█░░░░▄░▄█░░██░ ░░░░░░░▀█░░░░▄░░░░░██░░░░▄░░░▄░░▄░░░██░ ░░░░░░░▄█▄░░░░░░░░░░░▀▄░░▀▀▀▀▀▀▀▀░░▄▀░░ ░░░░░░█▀▀█████████▀▀▀▀████████████▀░░░░ ░░░░░░████▀░░███▀░░░░░░▀███░░▀██▀░░░░░░ ░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░
    
    ____________________________________________________________  
    Hello! I'm Neko-bot *meow*  
    What can I do for you?
    ____________________________________________________________


## 3. Features
 
**Command Format**

-   Words in  `UPPER_CASE`  are the parameters to be supplied by the user e.g. in  `todo TASKNAME`,  `TASKNAME`  is a parameter which can be used as  `todo homework`.
    
### 3.1 Listing all Tasks `list`
This will list all task which the user has added.

Format: `list`

Expected Outcome:
```
 1.[T][✗] join sports club
 2.[E][✗] project meeting (at: 2020/08/06 14:00:00)
 3.[D][✗] return book (by: 2020/10/17 01:15:00)
```
###  3.2 Adding a Todo task `todo`

This will add a Todo task to the task list.

Format: `todo task_name`  

Sample Input: `todo cook pasta for dinner`  

The expected outcome:
```
Got it. I've added this task:
[T][✗] cook pasta for dinner
You now have 4 tasks in the list.
```    

###  3.3 Adding an Event task `event`

This will add an event task to the task list.

Format:

1. `event task_name /at yyyy/MM/dd hh:mm:ss `

Sample Input:

1. `event Apple stock split /at 2020/09/3 10:00:15`

The expected outcome:

    Got it. I've added this task:
    [E][✗] Apple stock split (at: 2020/09/03 10:00:15)
    You now have 5 tasks in the list.

###  3.4 Adding a Deadline task `deadline`

This will add a deadline task to the task list

Format:
1. `deadline task_name /by yyyy/MM/dd hh:mm:ss`  
  
Sample Input:

1. `deadline CS2113 iP /by 2020/10/02 23:59:00`

The expected outcome:

    Got it. I've added this task:
    [D][✗] CS2113 iP (by: 2020/10/02 11:59:00)
    You now have 6 tasks in the list.
###  3.5 Completing a Task `done`

This will mark a task as completed.  

Format: `done task_id`  

Sample Input: `done 2`  

The expected outcome:

     Nice! I've marked this task as done: 
       [E][✓] project meeting (at: 2020/08/06 14:00:00)

###  3.6 Deleting a Task `delete`

This will remove a task from the task list.  

Format: `delete task_id`  

Sample Input: `delete 2`  

The expected outcome:

     Noted. I've removed this task: 
       [E][✓] project meeting (at: 2020/08/06 14:00:00)
     Now you have 5 tasks in the list.
###  3.7 Finding Task  `find`

Searches for all tasks containing specified string and prints them.  

Format: `find search_term`  

Sample Input: `find oo`  

The expected outcome:

    The following are results for keyword: oo
     2.[D][✗] return book (by: 2020/10/17 01:15:00)
     3.[T][✗] cook pasta for dinner

####  3.8 Find by Due Date `schedule`

Searches and prints all tasks that on a specified date.  

Format: `schedule yyyy/MM/dd`  

Sample Input: `schedule 2020/09/3`
 

The expected outcome:

     Your tasks on date Thu Sep 03 00:00:00 SGT 2020 are:
     4.[E][✗] Apple stock split (at: 2020/09/03 10:00:15)


###  3.9 Exiting the program `bye`

Terminates the program.

Format: `bye`

Sample Input: `bye`

The expected outcome:

     Bye. Hope to see you again soon!

## 4. FAQ

**Q**: Where are the tasks stored at after I close the program?
 **A**: They are located in `/data/tasks.txt` of the directory you ran the program in.

**Q**: How do I transfer my saved tasks list to another Computer?  
**A**: Run the application on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous `./data/tasks.txt` folder.

## 5. Command Summary

<table>
<tbody>
<tr>
<th>Command</th>
<th>Format &amp; Examples</th>
</tr>
<tr>
<td>list</td>
<td>`list`</td>
</tr>
<tr>
<td>todo</td>
<td>`todo [TASK_NAME]`<br />e.g. `todo cook pasta for dinner`</td>
</tr>
<tr>
<td>event</td>
<td>`event [TASK_NAME] [at/ yyyy/MM/dd hh:mm:ss]`<br />e.g. `event Apple stock split /at 2020/09/3 10:00:15`</td>
</tr>
<tr>
<td>deadline</td>
<td>`deadline [TASK_NAME] [by/ yyyy/MM/dd hh:mm:ss]`<br />e.g. `deadline CS2113 iP /by 2020/10/02 23:59:00`</td>
</tr>
<tr>
<td>done</td>
<td>`done &lt;INDEX&gt;`<br />e.g. `done 2`</td>
</tr>
<tr>
<td>delete</td>
<td>delete &lt;INDEX&gt;`<br />e.g. `delete 2`</td>
</tr>
<tr>
<td>find</td>
<td>`find &lt;SEARCH_TERM&gt;`<br />e.g. `find Apple`</td>
</tr>
<tr>
<td>schedule</td>
<td>`schedule &lt;yyyy/MM/dd&gt;`<br />e.g. `schedule 2020/09/3`</td>
</tr>
<tr>
<td>bye</td>
<td>`bye`</td>
</tr>
</tbody>
</table>