package main.hero;

public final class Pyromancer extends Hero {
  Pyromancer() {
    super();
  }

  @Override
  public void isAttackedBy(Hero hero) {
    hero.ability1(this);
    hero.ability2(this);
  }

  @Override
  public void ability1(Rogue rogue) {

  }

  @Override
  public void ability2(Rogue rogue) {

  }

  @Override
  public void ability1(Wizard wizard) {

  }

  @Override
  public void ability2(Wizard wizard) {

  }

  @Override
  public void ability1(Pyromancer pyromancer) {

  }

  @Override
  public void ability2(Pyromancer pyromancer) {

  }

  @Override
  public void ability1(Knight knight) {

  }

  @Override
  public void ability2(Knight knight) {

  }
}
