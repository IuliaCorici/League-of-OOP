package main.angel;

import main.hero.Hero;
import main.hero.Knight;
import main.hero.Pyromancer;
import main.hero.Rogue;
import main.hero.Wizard;

public interface AngelVisitor {
  void visit(Hero hero);
  void visit(Knight knight);
  void visit(Rogue rogue);
  void visit(Pyromancer pyromancer);
  void visit(Wizard wizard);
}
