# League-of-OOP
Similar to MMO and RPG games, each hero in the game has its class of ablities and is designed to fight against other heroes in a 2D-map, through the help of various Dessign Patterns.  
README from the first stage:  
--------> I have created a class named GameEngine that basically describes the flow of the game, placing all the heroes on the map. The MapoFGame is a class which is designed as a singleton,
because we only need an instance of it as it is a single map during the whole game.   
In addition, I have implemented a Factory for the land to easily access the land modifiers.  
----------> Following up, I have created an abstract class named Hero, thinking of it as a prototype for a hero that can be Rogue, Knight, Wizard or Pyromancer, using methods that are to be implemented as each hero wants 
(i.e. ‘isAttackedBy(hero) : ability1(x) , ability2(x)’, where ‘x’ can be one of the four heroes. The heroes are created based on a HeroFactory.  
-----------> This is why I decided that every type of hero will inherit the Hero class and using Double-Dispatch on the heroes, they can fight one another by calling the same method,
but implemented aș they want, without using instanceof.  
Rogue ___________INHERITANCE_____Hero  
Wizard _______|  
Knight   _______| (Double-Dispatch)  
Pyromancer ___|   

  
README from second stage:  

!At this stage, I have implemented several dessign patterns as it follows:  
1. Visitor Pattern for the Angels  
   
AngelVisitor       => visit(hero)              => visit(knight)  
=>visit(wizard) (interface)          => visit(rogue)            =>visit(pyromancer)     
I M P L E M E N T E D      IN  
Angel                          => visit(hero)              => visit(knight)     =>visit(wizard)  
(abstract class)               => visit(rogue)            =>visit(pyromancer)  
- location          - type          - name  
 

Due to the Abstract Class, I could implement every kind of Angel (DamageAngel, Dracula, GoodBoy, etc) and its different behavour on every type of Hero by just customizing the visit method.  În addition, every angel is created by an AngelFactory.  
  
2. Observer Pattern for the Great Magician  
  
OBSERVABLE  
Magician (class) =>  display(hero1, hero2)         - update(hero1, hero2)   
  
OBSERVERS  
  Observers (interface) =>   update(hero1, hero2)                
I M P L E M E N T E D        IN   
AngelObserver (class) =>   update(hero1, hero2)   
HeroObserver (class) =>   update(hero1, hero2)  
  
Due to the Observer Pattern, the Magician is notified of every change regarding both the Angels and Heroes. The AngelObserver prints what the angels have done during the round and if they helped/ hit/ revived/ killed a hero. The HeroObserver prints what has happened to the heroes after the battle.  
  
3. Strategy Pattern for choosing the hero’s strategy depending on their HP  
  
STRATEGY (interface) => prepareForBattle()  
IMPLEMENTED IN             
KnightStrategy (class) => prepareForBattle()   
PyromancerStrategy (class) =>prepareForBattle()  
RogueStrategy (class) => prepareForBattle()   
WizardStrategy (class) => prepareForBattle()  
Each hero has a Strategy field whch is accessed through the abstract method în Hero : ‘chooseStrategy’  thanks to whch that hero can ajust his HP and race modifiers according to its best.
