package ru.zvezdov.ocprof.chapter_1.AdvancedClassDesign.VirtulaMethodInvocation;

/**
 * @author Dmitry Zvezdov
 *         07.04.17.
 */
public class Bird extends AbstractAnimal {
    @Override
    public void feed() {
        addSeed();
    }

    public void addSeed(){

    }
}
