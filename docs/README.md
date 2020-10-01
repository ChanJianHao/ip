

#  Neko-bot  User Guide
By: `Chan Jian Hao` Since: `September 2020` License: `MIT`

![logo](https://i.imgur.com/Z2KOn6Y.png)

- [Neko-bot  User Guide](#neko-bot--user-guide)
  * [1. Introduction](#1-introduction)
  * [2. Quick Start with Intellij](#2-quick-start-with-intellij)
  * [3. Features](#3-features)
    + [3.1 Task Manager](#31-task-manager)
    + [3.2 Search](#32-search)
    + [3.3 Tasks Storage](#33-tasks-storage)
  * [4. Usage](#4-usage)
    + [4.1 Listing all Tasks `list`](#41-listing-all-tasks-list)
    + [4.2 Adding a Todo task `todo`](#42-adding-a-todo-task-todo)
    + [4.3 Adding an Event task `event`](#43-adding-an-event-task-event)
    + [4.4 Adding a Deadline task `deadline`](#44-adding-a-deadline-task-deadline)
    + [4.5 Completing a Task `done`](#45-completing-a-task-done)
    + [4.6 Deleting a Task `delete`](#46-deleting-a-task-delete)
    + [4.7 Finding Task  `find`](#47-finding-task-find)
    + [4.8 Find by Due Date `schedule`](#48-find-by-due-date-schedule)
    + [4.9 Exiting the program `bye`](#49-exiting-the-program-bye)
  * [5. FAQ](#5-faq)
  * [6. Command Summary](#6-command-summary)




## 1. Introduction

Neko-bot is a simple desktop application used for managing and tracking tasks (e.g., Todos, Events, Deadlines).



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

### 3.1 Task Manager

 1. Adds tasks (e.g., Todo, Deadline, or Event) to the list.
 2. Marks task as done.
 3. Removes task when it no longer needs tracking.
 4. Listing of all tasks.

### 3.2 Search

 1. Search for a task using keyword.
 2. Search for a task by providing a specific date.

### 3.3 Tasks Storage

 1. Automatically saves your task whenever you make changes.
 2. Available in a .txt file for easy reading and importing to other programs.



## 4. Usage
 
   
### 4.1 Listing all Tasks `list`
This will list all task which the user has added.

Format: `list`

Expected Outcome:
```
 1.[T][✗] join sports club
 2.[E][✗] project meeting (at: 2020/08/06 14:00:00)
 3.[D][✗] return book (by: 2020/10/17 01:15:00)
```

###  4.2 Adding a Todo task `todo`

This will add a Todo task to the task list.

Format: `todo <TASK_DESCRIPTION>`  

Sample Input: `todo cook pasta for dinner`  

The expected outcome:
```
Got it. I've added this task:
[T][✗] cook pasta for dinner
You now have 4 tasks in the list.
```    


###  4.3 Adding an Event task `event`

This will add an event task to the task list.

Format:

1. `event <TASK_DESCRIPTION> /at <yyyy/MM/dd hh:mm:ss> `

Sample Input:

1. `event Apple stock split /at 2020/09/3 10:00:15`

The expected outcome:

    Got it. I've added this task:
    [E][✗] Apple stock split (at: 2020/09/03 10:00:15)
    You now have 5 tasks in the list.


###  4.4 Adding a Deadline task `deadline`

This will add a deadline task to the task list.

Format:
1. `deadline <TASK_DESCRIPTION> /by <yyyy/MM/dd hh:mm:ss>`  
  
Sample Input:

1. `deadline CS2113 iP /by 2020/10/02 23:59:00`

The expected outcome:

    Got it. I've added this task:
    [D][✗] CS2113 iP (by: 2020/10/02 11:59:00)
    You now have 6 tasks in the list.


###  4.5 Completing a Task `done`

This will mark a task as completed.  

Format: `done <TASK_INDEX>`  

Sample Input: `done 2`  

The expected outcome:

     Nice! I've marked this task as done: 
       [E][✓] project meeting (at: 2020/08/06 14:00:00)


###  4.6 Deleting a Task `delete`

This will remove a task from the task list.  

Format: `delete <TASK_INDEX>`  

Sample Input: `delete 2`  

The expected outcome:

     Noted. I've removed this task: 
       [E][✓] project meeting (at: 2020/08/06 14:00:00)
     Now you have 5 tasks in the list.


###  4.7 Finding Task  `find`

Searches for all tasks containing specified keyword and prints them.  

Format: `find <KEYWORD>`  

Sample Input: `find oo`  

The expected outcome:

    The following are results for keyword: oo
     2.[D][✗] return book (by: 2020/10/17 01:15:00)
     3.[T][✗] cook pasta for dinner


####  4.8 Find by Due Date `schedule`

Searches and prints all tasks that on a specified date.  

Format: `schedule <yyyy/MM/dd>`  

Sample Input: `schedule 2020/09/3`
 

The expected outcome:

     Your tasks on date Thu Sep 03 00:00:00 SGT 2020 are:
     4.[E][✗] Apple stock split (at: 2020/09/03 10:00:15)



###  4.9 Exiting the program `bye`

Terminates the program.

Format: `bye`

Sample Input: `bye`

The expected outcome:

     Bye. Hope to see you again soon!



## 5. FAQ


**Q**: Where are the tasks stored at after I close the program?

> They are located in `./data/tasks.txt` of the directory you ran the program in.

 

**Q**: How do I transfer my saved tasks list to another Computer?  

> Run the application on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous `./data/tasks.txt` folder.



## 6. Command Summary

|Command                     |Format & Examples                                               |
|---                                   |---                                                   |
| list                      | `list`                             |
| todo                  | `todo <TASK_DESCRIPTION>` e.g. `todo cook pasta for dinner`  |
| event                    | `event <TASK_DESCRIPTION> /at <yyyy/MM/dd hh:mm:ss>` e.g. `event Apple stock split /at 2020/09/3 10:00:15`     |
| deadline                  | `deadline <TASK_DESCRIPTION> /by <yyyy/MM/dd hh:mm:ss>` e.g. `deadline CS2113 iP /by 2020/10/02 23:59:00`                                   |
| done                        | `done <TASK_INDEX>` e.g. `done 2`                                 |
| delete               | delete `<TASK_INDEX>` e.g. `delete 2`                                 |
| find  | `find <KEYWORD>` e.g. `find Apple`                                 |
| schedule                       | `schedule <yyyy/MM/dd>` e.g. `schedule 2020/09/3`                                                |
| bye                                 |`bye`                                                 |
