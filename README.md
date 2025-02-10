# Blocky Game

_(Gamewerks corporation internal code—do not share!)_

## Credits

Primary developer: _Lauren Ajax_

### Resources Used

+ Java 17.0.14
+ The version of Java I used to write this program.
+ Apache Netbeans
+ The program I used to develop my code.
+ Project: The World's Greatest Internship
+ https://osera.cs.grinnell.edu/ttap/data-structures-labs/the-worlds-best-internship.html
+ The project page that gave me instructions on how to do the project.
+ POsera Blocky Repository
+ https://github.com/psosera/blocky
+ The place where I received the starting code for the project.
+ My Blocky Repository
+ https://github.com/LaurenAjax/blocky
+ The place I cloned the starting code from.
+ Github
+ https://github.com/
+ The site these repositories live on.
+ How to Upload/Push/Add NetBeans Java Project to Github
+ https://mauricemuteti2015.medium.com/how-to-upload-push-add-netbeans-java-project-to-github-d3c098922663
+ How I extracted the starting code from Github and moved the finished code back.

## Changelog

~~~console
tarski blocky$ git log 
commit 18d4144276a5f0d5714cb0783e303b87e17828d6 (HEAD -> main)
Author: ajaxlaur <ajaxlaur@132.161.196.207>
Date:   Sun Feb 9 20:01:31 2025 -0600

    Send to Git.
                                                                                                                         
commit 173d1c63a9be69b26c9c0d4a3f22eb60af104dc2 (HEAD -> main)
Author: ajaxlaur <ajaxlaur@132.161.196.207>
Date:   Sun Feb 9 19:13:24 2025 -0600

    Adjusted range of isValidPosition to be contained within board (well.length 
    - 4). Changed deleteRow to move downward by making the row for loop be 
    n >= row >0 and and switched the locations of its for statements.

commit 407e406766d646f20274658f2eeed6f8bfdef6db
Author: ajaxlaur <ajaxlaur@132.161.196.207>
Date:   Sun Feb 9 19:11:23 2025 -0600                                                                                                           
                                                                                                                                                
    Adjusted spawnpoint to start at valid location (Board Height - 19). Altered 
    gravity to move downward by setting nextPos in processGravity to add 1 not 
    -1.                                                           
                                                                                                                                                
commit b1ca461b518df99c74254a056b837376be158a7a                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Sun Feb 9 16:28:39 2025 -0600                                                                                                           
                                                                                                                                                
    Undid reversed gravity to work out bugs and unadjusted board.collides.                                                                                                    
                                                                                                                                                
commit 672ac37443063901f3422e5152f9e5c6f00b3559                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Thu Feb 6 23:34:31 2025 -0600                                                                                                           
                                                                                                                                                
    Adjusted board.collides calls to go to the one that takes one parameter.                                                                          
                                                                                                                                                
commit 84f915c59bbea00e0e5d47a8b1c2ed281ad0712f                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Thu Feb 6 23:24:24 2025 -0600                                                                                                           
                                                                                                                                                
    Added processMovement() to step and a break to case RIGHT of 
    processMovement.                                                              
                                                                                                                                                
commit c57e61a4ddfa1984720438f2f809a6cd49382d71                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Thu Feb 6 00:43:41 2025 -0600                                                                                                           
                                                                                                                                                
    Stopped dropping block at the end of the screen by making isValidPosition
    row >= 0 && row < well.length.                                                                                            
                                                                                                                                                
commit 6a92ab5602931d9f8fd0814302f51c151109ac2b                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Thu Feb 6 00:37:30 2025 -0600                                                                                                           
                                                                                                                                                
    Switched gravity to go down with processGravity adding 1 and moved 
    spawnpoint for block to the top of the screen by subtracting 19.                                                        
                                                                                                                                                
commit a988bb1f98455d5c2b6cd49cb3c44c8be53dc635                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Wed Feb 5 23:52:53 2025 -0600                                                                                                           
                                                                                                                                                
    Changed col < 5 to col < 4 in public class Loader. Readded trySpawnBlock to 
    BlockyGame.                                                                                       
                                                                                                                                                
commit 584766aacc97ec57984cd79956800837b0ff6942                                                                                                 
Author: ajaxlaur <ajaxlaur@132.161.196.207>                                                                                                     
Date:   Wed Feb 5 23:46:18 2025 -0600                                                                                                           
                                                                                                                                                
    Deleted trySpawnBlock from BlockyGame to prevent a preemptive call.                                                                         
                                                                                                                                                
commit 21efa1b57d4ebede094644493aa2c7559c71883b (origin/main)                                                                                   
Author: Peter-Michael Osera <osera@cs.grinnell.edu>                                                                                             
Date:   Wed Feb 5 09:17:30 2025 -0600                                                                                                           
                                                                                                                                                
    hey is this how I use git, what is a commit?                                                                                                
                                                                                                                                                
commit 567a65a7ce173cf4c0a232aad9c22ab38ca66331                                                                                                 
Author: Peter-Michael Osera <osera@cs.grinnell.edu>                                                                                             
Date:   Wed Feb 5 09:13:20 2025 -0600                                                                                                           
                                                                                                                                                
    initial commit                                                                                                                              
(END)                                                                                          
~~~
