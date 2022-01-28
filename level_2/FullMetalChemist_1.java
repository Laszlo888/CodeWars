import java.util.*;

public class FullMetalChemist {
    public static void main(String[] args) {
        Molecule molecule = new Molecule("Biotin");

        molecule.brancher(9, 4, 4);
        try {
            molecule.bounder(new T(3, 3, 9, 1), new T(8, 1, 6, 1));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.add(new T(3, 2, "H"), new T(4, 2, "P"), new T(4, 2, "O"));
        } catch (LockedMolecule e) {
            System.out.println("Adding LockedMolecule catched");
        }
        molecule.brancher(4, 7);
        try {
            molecule.addChaining(2, 3, "O");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        try {
            molecule.add(new T(1, 4, "F"), new T(4, 1, "Br"));
        } catch (InvalidBond e) {
            System.out.println("Adding InvalidBond catched");
        }
        try {
            molecule.bounder(new T(2, 4, 3, 2), new T(3, 4, 3, 4), new T(3, 2, 2, 1), new T(1, 4, 5, 5), new T(2, 4, 2, 1));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        molecule.brancher(2, 1, 4);
        try {
            molecule.addChaining(2, 2, "P", "H", "O");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        try {
            molecule.addChaining(1, 7, "O", "Mg");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        molecule.brancher(8, 5, 3);
        try {
            molecule.bounder(new T(1, 10, 3, 10));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.bounder(new T(1, 7, 3, 8), new T(4, 1, 3, 8));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.mutate(new T(4, 4, "H"), new T(5, 1, "Br"), new T(3, 3, "P"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.addChaining(3, 4, "N");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        try {
            molecule.addChaining(1, 3, "B");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        molecule.brancher(8, 6);
        try {
            molecule.bounder(new T(1, 2, 1, 8), new T(5, 5, 4, 5), new T(3, 8, 3, 8), new T(2, 6, 1, 6), new T(3, 5, 2, 12));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.mutate(new T(1, 6, "B"), new T(2, 1, "S"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.mutate(new T(4, 10, "O"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.mutate(new T(3, 11, "Cl"), new T(7, 5, "B"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.bounder(new T(2, 11, 4, 2), new T(2, 13, 3, 9), new T(3, 3, 2, 11), new T(5, 1, 4, 2), new T(7, 9, 2, 11));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.addChaining(6, 9, "S", "P", "S");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        try {
            molecule.bounder(new T(4, 12, 9, 1), new T(3, 8, 7, 1), new T(2, 5, 4, 3));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.mutate(new T(1, 11, "H"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.mutate(new T(1, 1, "Br"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.add(new T(3, 3, "H"), new T(1, 4, "F"), new T(2, 11, "S"));
        } catch (InvalidBond e) {
            System.out.println("Adding InvalidBond catched");
        }
        try {
            molecule.bounder(new T(1, 8, 4, 9), new T(1, 7, 6, 1), new T(2, 1, 3, 8));
        } catch (InvalidBond e) {
            System.out.println("Bounder InvalidBond catched");
        }
        try {
            molecule.addChaining(8, 1, "F", "Cl");
        } catch (InvalidBond e) {
            System.out.println("AddChain InvalidBond catched");
        }
        try {
            molecule.mutate(new T(4, 4, "O"), new T(3, 4, "F"), new T(2, 6, "Mg"));
        } catch (InvalidBond e) {
            System.out.println("Mutator InvalidBond catched");
        }
        try {
            molecule.add(new T(1, 6, "Mg"), new T(2, 2, "F"));
        } catch (InvalidBond e) {
            System.out.println("Adding InvalidBond catched");
        }
    }
}

class Atom {

    public int id;
    public String element;
    public List<String> boundedAtoms = new ArrayList<>();

    public Atom(String elt, int id_) {
        element = elt;
        id = id_;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Atom(");
        if (boundedAtoms.size() == 0) {
            result.append(element).append(".").append(id).append(")");
        } else {
            result.append(element).append(".").append(id).append(": ");
            for (String b : boundedAtoms) {
                result.append(b).append(",");
            }
            result.deleteCharAt(result.length() - 1);
            result.append(")");
        }
        return result.toString();
    }

    @Override
    public int hashCode() {
        return id;
    }        //  Do not modify this method!!

    @Override
    public boolean equals(Object other) {       //  Do not modify this method!!
        if (other != null && other instanceof Atom) {
            Atom that = (Atom) other;
            return id == that.id;
        }
        return false;
    }
}


class Molecule {

    List<Atom> atoms = new ArrayList<>();
    private String name;
    private Map<String, Double> atomicWeights = new HashMap<>() {{
        put("H", 1.0); put("B", 10.8); put("C", 12.0); put("N", 14.0); put("O", 16.0);
        put("F", 19.0); put("Mg", 24.3); put("P", 31.0); put("S", 32.1); put("Cl", 35.5); put("Br", 80.0);
    }};

    private Map<String, Integer> atomicValence = new HashMap<>() {{
        put("H", 1); put("B", 3); put("C", 4); put("N", 3); put("O", 2);
        put("F", 1); put("Mg", 2); put("P", 3); put("S", 2); put("Cl", 1); put("Br", 1);
    }};
    boolean locked = false;


    public String getFormula() {
        System.out.println("Formulakérés meghívódott");
        if (locked == false) {
            throw new UnlockedMolecule();
        } else {
            StringBuilder result = new StringBuilder();
            int counter = 0;
            List<String> at = new ArrayList<>(Arrays.asList("C", "H", "O", "B", "Br", "Cl", "F", "Mg", "N", "P", "S"));
            for (int i = 0; i < at.size(); i++) {
                for (int j = 0; j < atoms.size(); j++) {
                    if (atoms.get(j).element.equals(at.get(i))) {
                        counter++;
                    }
                }
                if (counter == 1) {
                    result.append(at.get(i));
                } else if (counter > 1) {
                    result.append(at.get(i) + counter);
                }
                counter = 0;
            }
            return result.toString();
        }
    }

    public double getMolecularWeight() {
        System.out.println("Molekulasúly meghívódott");
        if (locked == false) {
            throw new UnlockedMolecule();
        } else {

            double result = 0;
            for (int i = 0; i < atoms.size(); i++) {
                result += atomicWeights.get(atoms.get(i).element);
            }
            return result;
        }
    }

    public List<Atom> getAtoms() {
        System.out.println("Atomlista meghívódott");
        return atoms;
    }

    public String getName() {
        System.out.println("Névkérés meghívódott");
        return name;
    }

    //constructors:
    public Molecule() {
        System.out.println("Konstruktor név nélküli");
        name = "";
    }

    public Molecule(String name) {
        System.out.println("Konstruktor meghívódott");
        this.name = name;
    }

    // brancher:  branch index, n'th atom in branch, n'th atom in molecule, atom sign
    Map<Integer, Map<Integer, Map<Integer, String>>> branchesAndAtomicIndexes = new LinkedHashMap<>();

    // basic chain with 'C' atoms in different branches
    public Molecule brancher(int... args) {
        System.out.println("Brancher: " + Arrays.toString(args));
        if (locked == false) {
            List<Integer> highestIndexInBranches = new ArrayList<>();
            int counter = atoms.size();
            int mapSize;
            if (branchesAndAtomicIndexes.isEmpty()) {
                mapSize = 0;
            } else {
                mapSize = branchesAndAtomicIndexes.size();
            }

            for (int i = 0; i < args.length; i++) {
                highestIndexInBranches.add(args[i]);
                branchesAndAtomicIndexes.put(i + 1 + mapSize, new LinkedHashMap<>());
                for (int j = 1; j <= args[i]; j++) {
                    branchesAndAtomicIndexes.get(i + 1 + mapSize).put(j, new LinkedHashMap<>());
                    counter++;
                    branchesAndAtomicIndexes.get(i + 1 + mapSize).get(j).put(counter, "C");
                    atoms.add(new Atom("C", counter));
                    if (args[i] != 1) {
                        if (j == 1) {
                            atoms.get(counter - 1).boundedAtoms.add("C" + (counter + 1));
                        } else if (j == highestIndexInBranches.get(i)) {
                            atoms.get(counter - 1).boundedAtoms.add("C" + (counter - 1));
                        } else {
                            atoms.get(counter - 1).boundedAtoms.add("C" + (counter - 1));
                            atoms.get(counter - 1).boundedAtoms.add("C" + (counter + 1));
                        }
                    }
                }
            }
        } else {
            throw new LockedMolecule();
        }
        return this;
    }

    Map<Integer, String> mutatedToHydrogenAndBoundingElement = new LinkedHashMap<>();

    // bounding branches to each other and if needed bound atoms
    // example: T(2,1,1,2) means: bound (second atom , from first branch , with first atom , from second branch)
    public Molecule bounder(T... args) {
        System.out.println("Bounder: " + Arrays.toString(args));
        if (locked == false) {

            int atomicNumberFirst, atomicNumberSecond, valenceFirst, valenceSecond, occupiedValenceFirst, occupiedValenceSecond;
            int branchSizeFirst, branchSizeSecond, atomsInBranchSizeFirst, atomsInBranchSizeSecond;
            StringBuilder atomFirst = new StringBuilder();
            StringBuilder atomSecond = new StringBuilder();

            for (T t : args) {
                branchSizeFirst = branchesAndAtomicIndexes.size();
                atomsInBranchSizeFirst = 0;
                branchSizeSecond = branchesAndAtomicIndexes.size();
                atomsInBranchSizeSecond = 0;
                if (branchSizeFirst >= t.b1) {
                    atomsInBranchSizeFirst = branchesAndAtomicIndexes.get(t.b1).size();
                }
                if (branchSizeSecond >= t.b2) {
                    atomsInBranchSizeSecond = branchesAndAtomicIndexes.get(t.b2).size();
                }
                if (branchSizeFirst < t.b1 || branchSizeSecond < t.b2 || atomsInBranchSizeFirst < t.c1 || atomsInBranchSizeSecond < t.c2) {
                    throw new InvalidBond();
                } else {
                    atomicNumberFirst = atomicNumberFinder(t.b1, t.c1);
                    atomFirst.append(branchesAndAtomicIndexes.get(t.b1).get(t.c1).get(atomicNumberFirst));
                    valenceFirst = atomicValence.get(atomFirst.toString());
                    occupiedValenceFirst = atoms.get(atomicNumberFirst - 1).boundedAtoms.size();
                    atomicNumberSecond = atomicNumberFinder(t.b2, t.c2);
                    atomSecond.append(branchesAndAtomicIndexes.get(t.b2).get(t.c2).get(atomicNumberSecond));
                    valenceSecond = atomicValence.get(atomSecond.toString());
                    occupiedValenceSecond = atoms.get(atomicNumberSecond - 1).boundedAtoms.size();

                    if ((t.c1 == t.c2 && t.b1 == t.b2) ||
                            (valenceFirst - occupiedValenceFirst == 0) ||
                            (valenceSecond - occupiedValenceSecond == 0)) {
                        throw new InvalidBond();
                    } else {
                        atoms.get(atomicNumberSecond - 1).boundedAtoms.add(atomFirst.toString() + atomicNumberFirst);
                        atoms.get(atomicNumberFirst - 1).boundedAtoms.add(atomSecond.toString() + atomicNumberSecond);
                        // change mutation map if we bound to H
                        if (atomFirst.toString().equals("H") && atomSecond.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atomicNumberFirst, "H" + atomicNumberSecond);
                            mutatedToHydrogenAndBoundingElement.put(atomicNumberSecond, "H" + atomicNumberFirst);
                        } else if ((atomFirst.toString().equals("H") && !atomSecond.toString().equals("H"))) {
                            mutatedToHydrogenAndBoundingElement.put(atomicNumberFirst, atomSecond.toString() + atomicNumberSecond);

                        } else if (atomSecond.toString().equals("H") && !atomFirst.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atomicNumberSecond, atomFirst.toString() + atomicNumberFirst);
                        }
                        listOfAtomsOrderingBoundedAtoms(atomicNumberFirst);
                        listOfAtomsOrderingBoundedAtoms(atomicNumberSecond);
                        atomFirst.delete(0, atomFirst.length());
                        atomSecond.delete(0, atomSecond.length());
                    }
                }
            }
        } else {
            throw new LockedMolecule();
        }
        return this;
    }

    // frequent variables
    StringBuilder atomSign = new StringBuilder();
    int atomicIndexOfOriginalAtom, valence, occupiedValence;

    // mutation(change atoms to other atom): T(1,2,"O") means (change first atom, from second branch, to Oxygen)
    public Molecule mutate(T... args) {
        System.out.println("Mutate: " + Arrays.toString(args));
        if (locked == false) {
            for (T t : args) {
                atomSign.delete(0, atomSign.length());
                int branchSize = branchesAndAtomicIndexes.size();
                int atomsInBranchSize = 0;
                if (branchSize >= t.nb) {
                    atomsInBranchSize = branchesAndAtomicIndexes.get(t.nb).size();
                }
                if (branchSize < t.nb || atomsInBranchSize < t.nc) {
                    throw new InvalidBond();
                } else {
                    atomicIndexOfOriginalAtom = atomicNumberFinder(t.nb, t.nc);
                    atomSign.append(branchesAndAtomicIndexes.get(t.nb).get(t.nc).get(atomicIndexOfOriginalAtom));
                    int currentlyBoundedAtoms = atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.size();
                    valence = atomicValence.get(t.elt);

                    if (valence < currentlyBoundedAtoms) {
                        throw new InvalidBond();
                    } else {
                        if (!atomSign.toString().equals(t.elt)) {
                            // change atom in branch map
                            branchesAndAtomicIndexes.get(t.nb).get(t.nc).put(atomicIndexOfOriginalAtom, t.elt);

                            // change in atom list
                            atoms.get(atomicIndexOfOriginalAtom - 1).element = t.elt;


                            // change original atom to new one in bound's bounds
                            // change not H Atom
                            if (!atomSign.toString().equals("H")) {
                                if (atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.size() == 0 && t.elt.equals("H")) {
                                    mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, null);
                                }
                                if (atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.size() > 0) {
                                    List<Integer> ls;
                                    for (int i = 0; i < atoms.size(); i++) {
                                        ls = new ArrayList<>();
                                        for (int j = 0; j < atoms.get(i).boundedAtoms.size(); j++) {
                                            if (atoms.get(i).boundedAtoms.get(j).equals(atomSign.toString() + atomicIndexOfOriginalAtom)) {
                                                if (atoms.get(i).element.equals("H")) {
                                                    mutatedToHydrogenAndBoundingElement.put(searchMutationHydrogenMap(), t.elt + atomicIndexOfOriginalAtom);
                                                }
                                                ls.add(j);
                                            }
                                        }
                                        if (ls.size() != 0) {
                                            for (int l = 0; l < ls.size(); l++) {
                                                if (t.elt.equals("H")) {
                                                    atoms.get(i).boundedAtoms.set(ls.get(l), t.elt);
                                                    if (!atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.get(0).equals("H")) {
                                                        mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.get(0));
                                                    } else {
                                                        mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, t.elt + searchMutationHydrogenMap());
                                                    }
                                                } else {
                                                    atoms.get(i).boundedAtoms.set(ls.get(l), t.elt + atomicIndexOfOriginalAtom);
                                                }
                                            }
                                            listOfAtomsOrderingBoundedAtoms(i + 1);
                                        }
                                    }
                                }
                            }
                            // change H atom to something else
                            else if (atomSign.toString().equals("H")) {
                                // because we change H only one bound can be
                                if (atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.size() > 0) {
                                    StringBuilder whatIsBoundedToH = new StringBuilder();
                                    whatIsBoundedToH.append(atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.get(0));
                                    if (!whatIsBoundedToH.toString().equals("H")) { // example: H1:C2
                                        int wasBoundedToHydrogen = 0;
                                        int indexH = 0;
                                        if (whatIsBoundedToH.toString().matches("[A-Z][1-9]([0-9])?")) {
                                            wasBoundedToHydrogen = Integer.parseInt(whatIsBoundedToH.toString().substring(1));
                                            for (int i = 0; i < atoms.get(wasBoundedToHydrogen - 1).boundedAtoms.size(); i++) {
                                                if (atoms.get(wasBoundedToHydrogen - 1).boundedAtoms.get(i).equals("H")) {
                                                    indexH = i;
                                                    break;
                                                }
                                            }
                                            atoms.get(wasBoundedToHydrogen - 1).boundedAtoms.set(indexH, t.elt + atomicIndexOfOriginalAtom);
                                        } else if (whatIsBoundedToH.toString().matches("[A-Z][a-z][1-9]([0-9])?")) {
                                            wasBoundedToHydrogen = Integer.parseInt(whatIsBoundedToH.toString().substring(2));
                                            for (int i = 0; i < atoms.get(wasBoundedToHydrogen - 1).boundedAtoms.size(); i++) {
                                                if (atoms.get(wasBoundedToHydrogen - 1).boundedAtoms.get(i).equals("H")) {
                                                    indexH = i;
                                                    break;
                                                }
                                            }
                                            atoms.get(wasBoundedToHydrogen - 1).boundedAtoms.set(indexH, t.elt + atomicIndexOfOriginalAtom);
                                        }
                                        listOfAtomsOrderingBoundedAtoms(wasBoundedToHydrogen);
                                        mutatedToHydrogenAndBoundingElement.remove(atomicIndexOfOriginalAtom);
                                    } else { // example H1:H
                                        atoms.get(searchMutationHydrogenMap() - 1).boundedAtoms.set(0, t.elt + atomicIndexOfOriginalAtom);
                                        mutatedToHydrogenAndBoundingElement.remove(atomicIndexOfOriginalAtom);
                                        mutatedToHydrogenAndBoundingElement.put(searchMutationHydrogenMap(), t.elt + atomicIndexOfOriginalAtom);
                                    }
                                } else {
                                    mutatedToHydrogenAndBoundingElement.remove(atomicIndexOfOriginalAtom);
                                }
                            }
                            atomSign.delete(0, atomSign.length());
                        }
                    }
                }
            }
        } else {
            throw new LockedMolecule();
        }
        return this;
    }

    public int searchMutationHydrogenMap() {
        int atomIndex = 0;
        for (Map.Entry<Integer, String> entry : mutatedToHydrogenAndBoundingElement.entrySet()) {
            if (entry.getValue().matches("[A-Z][1-9]([0-9])?")) {
                if (Integer.parseInt(entry.getValue().substring(1)) == atomicIndexOfOriginalAtom) {
                    atomIndex = entry.getKey();
                }
            } else if (entry.getValue().matches("[A-Z][a-z][1-9]([0-9])?")) {
                if (Integer.parseInt(entry.getValue().substring(2)) == atomicIndexOfOriginalAtom) {
                    atomIndex = entry.getKey();
                }
            }
        }
        return atomIndex;
    }

    public int atomicNumberFinder(int branch, int nb) {
        Integer atomicNumber = 0;
        outer:
        for (Map.Entry<Integer, Map<Integer, Map<Integer, String>>> entry : branchesAndAtomicIndexes.entrySet()) {
            Integer branchIndex = entry.getKey();
            Map<Integer, Map<Integer, String>> value = entry.getValue();
            for (Map.Entry<Integer, Map<Integer, String>> e : value.entrySet()) {
                Integer number = e.getKey();
                Map<Integer, String> value2 = e.getValue();
                for (Map.Entry<Integer, String> e2 : value2.entrySet()) {
                    if (branchIndex == branch && number == nb) {
                        atomicNumber = e2.getKey();
                        break outer;
                    }
                }
            }
        }
        return atomicNumber;
    }

    // add a single atom (it is not considered being part of a branch) example: new T(2, 2, "B")
    public Molecule add(T... args) {
        System.out.println("Add atom: " + Arrays.toString(args));
        if (locked == false) {
            atomSign.delete(0, atomSign.length());
            for (T t : args) {
                int branchSize = branchesAndAtomicIndexes.size();
                int atomsInBranchSize = 0;
                if (branchSize >= t.nb) {
                    atomsInBranchSize = branchesAndAtomicIndexes.get(t.nb).size();
                }
                if (branchSize < t.nb || atomsInBranchSize < t.nc) {
                    throw new InvalidBond();
                } else {
                    atomicIndexOfOriginalAtom = atomicNumberFinder(t.nb, t.nc);
                    atomSign.append(branchesAndAtomicIndexes.get(t.nb).get(t.nc).get(atomicIndexOfOriginalAtom));
                    valence = atomicValence.get(atomSign.toString());
                    occupiedValence = atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.size();
                    if (valence - occupiedValence == 0) {
                        throw new InvalidBond();
                    } else {
                        atoms.add(new Atom(t.elt, atoms.size() + 1));
                        atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.add(t.elt + atoms.size());
                        atoms.get(atoms.size() - 1).boundedAtoms.add(atomSign.toString() + atomicIndexOfOriginalAtom);
                        // change mutation map if add to H
                        if (!t.elt.equals("H") && atomSign.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, t.elt + atoms.size());
                        } else if (t.elt.equals("H") && !atomSign.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atoms.size(), atomSign.toString() + atomicIndexOfOriginalAtom);
                        } else if (t.elt.equals("H") && atomSign.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atoms.size(), atomSign.toString() + atomicIndexOfOriginalAtom);
                            mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, t.elt + atoms.size());
                        }
                        listOfAtomsOrderingBoundedAtoms(atomicIndexOfOriginalAtom);
                        atomSign.delete(0, atomSign.length());
                    }
                }
            }
        } else {
            throw new LockedMolecule();
        }
        return this;
    }


    // add a chain of atoms, example: (2,3,"N","S","Br") will (add on 2nd atom, from 3rd branch, -N-S-Br)
    // chain is not considered being part of any branch
    public Molecule addChaining(int nc, int nb, String... atom) {
        System.out.println("Add chain - atomszáma: " + nc + " branch száma: " + nb + " atomok: " + Arrays.toString(atom));
        if (locked == false) {
            atomSign.delete(0, atomSign.length());
            int branchSize = branchesAndAtomicIndexes.size();
            int atomsInBranchSize = 0;
            if (branchSize >= nb) {
                atomsInBranchSize = branchesAndAtomicIndexes.get(nb).size();
            }
            if (branchSize < nb || atomsInBranchSize < nc) {
                throw new InvalidBond();
            } else {
                atomicIndexOfOriginalAtom = atomicNumberFinder(nb, nc);
                atomSign.append(branchesAndAtomicIndexes.get(nb).get(nc).get(atomicIndexOfOriginalAtom));
                valence = atomicValence.get(atomSign.toString());
                occupiedValence = atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.size();

                if (valence - occupiedValence == 0) {
                    throw new InvalidBond();
                } else {
                    boolean isAnyAtomWithOneValenceInChain = false;
                    for (int i = 0; i < atom.length - 1; i++) {
                        if (atomicValence.get(atom[i]) == 1) {
                            isAnyAtomWithOneValenceInChain = true;
                            break;
                        }
                    }
                    if (isAnyAtomWithOneValenceInChain == true) {
                        throw new InvalidBond();
                    } else {
                        // first atom of chain will be bounded to nb,nc atom
                        //change in mutation map if bound to h
                        if (!atom[0].equals("H") && atomSign.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, atom[0] + (atoms.size() + 1));
                        } else if (atom[0].equals("H") && !atomSign.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atoms.size() + 1, atomSign.toString() + atomicIndexOfOriginalAtom);
                        } else if (atom[0].equals("H") && atomSign.toString().equals("H")) {
                            mutatedToHydrogenAndBoundingElement.put(atoms.size() + 1, atomSign.toString() + atomicIndexOfOriginalAtom);
                            mutatedToHydrogenAndBoundingElement.put(atomicIndexOfOriginalAtom, atom[0] + atoms.size() + 1);
                        }

                        // add to list of atoms
                        int number = atoms.size() + 1;
                        for (int i = 0; i < atom.length; i++) {
                            atoms.add(new Atom(atom[i], number));
                            if (i == 0 && atom.length == 1) {
                                atoms.get(atoms.size() - 1).boundedAtoms.add(atomSign.toString() + atomicIndexOfOriginalAtom);
                                atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.add(atom[0] + atoms.size());
                                listOfAtomsOrderingBoundedAtoms(atomicIndexOfOriginalAtom);
                            } else if (i == 0) {
                                atoms.get(atoms.size() - 1).boundedAtoms.add(atomSign.toString() + atomicIndexOfOriginalAtom);
                                atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.add(atom[0] + atoms.size());
                                listOfAtomsOrderingBoundedAtoms(atomicIndexOfOriginalAtom);
                            } else if (i == atom.length - 1) {
                                atoms.get(atoms.size() - 1).boundedAtoms.add(atoms.get(atoms.size() - 2).element + atoms.get(atoms.size() - 2).id);
                                atoms.get(atoms.size() - 2).boundedAtoms.add(atoms.get(atoms.size() - 1).element + atoms.get(atoms.size() - 1).id);
                                if (atom[atom.length - 1].equals("H")) {
                                    mutatedToHydrogenAndBoundingElement.put(number, atom[atom.length - 2] + (number - 1));
                                }
                                listOfAtomsOrderingBoundedAtoms(atoms.size() - 1);
                                listOfAtomsOrderingBoundedAtoms(atoms.size() - 2);
                            } else {
                                atoms.get(atoms.size() - 1).boundedAtoms.add(atoms.get(atoms.size() - 2).element + atoms.get(atoms.size() - 2).id);
                                atoms.get(atoms.size() - 2).boundedAtoms.add(atoms.get(atoms.size() - 1).element + atoms.get(atoms.size() - 1).id);
                                listOfAtomsOrderingBoundedAtoms(atoms.size() - 1);
                                listOfAtomsOrderingBoundedAtoms(atoms.size() - 2);
                            }
                            number++;
                        }
                    }
                }
            }
        } else {
            throw new LockedMolecule();
        }
        atomSign.delete(0, atomSign.length());
        return this;
    }

    // adding hydrogens and lock molecule till unlock
    public Molecule closer() {
        System.out.println("Closer meghívódott");
        if (locked == false) {
            List<Atom> hydrogenAdderToAtomList = new ArrayList<>();
            List<String> whatIsBoundedToHydrogen = new ArrayList<>();
            int hydrogenNeeded;
            int currentStartPoint = atoms.size();
            int hCounter = atoms.size() + 1;

            atomSign.delete(0, atomSign.length());
            for (int i = 0; i < atoms.size(); i++) {
                atomicIndexOfOriginalAtom = i + 1;
                atomSign.append(atoms.get(i).element);
                valence = atomicValence.get(atomSign.toString());
                occupiedValence = atoms.get(i).boundedAtoms.size();
                hydrogenNeeded = valence - occupiedValence;
                if (hydrogenNeeded == 1) {
                    atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.add("H");
                    hydrogenAdderToAtomList.add(new Atom("H", hCounter));
                    whatIsBoundedToHydrogen.add(atomSign.toString() + atomicIndexOfOriginalAtom);
                    hCounter++;
                } else if (hydrogenNeeded > 1) {
                    for (int j = 0; j < hydrogenNeeded; j++) {
                        atoms.get(atomicIndexOfOriginalAtom - 1).boundedAtoms.add("H");
                        hydrogenAdderToAtomList.add(new Atom("H", hCounter));
                        whatIsBoundedToHydrogen.add(atomSign.toString() + atomicIndexOfOriginalAtom);
                        hCounter++;
                    }
                }
                atomSign.delete(0, atomSign.length());
            }
            atoms.addAll(hydrogenAdderToAtomList);
            hydrogenAdderToAtomList.clear();
            for (int i = currentStartPoint; i < atoms.size(); i++) {
                atoms.get(i).boundedAtoms.add(whatIsBoundedToHydrogen.get(i - currentStartPoint));
            }

            locked = true;
        } else {
            throw new LockedMolecule();
        }
        return this;
    }


    // unlock molecule and remove all hydrogen and if left any empty branch remove them as well
    public Molecule unlock() {
        System.out.println("Unlock meghívódott");
        if (locked == true) {
            atomSign.delete(0, atomSign.length());
            // remove hydrogen from atom list
            StringBuilder sb = new StringBuilder();
            Iterator<Atom> at = atoms.iterator();
            while (at.hasNext()) {
                sb.append(at.next().element);
                if (sb.toString().charAt(0) == ('H')) {
                    at.remove();
                }
                sb.delete(0, sb.length());
            }

            // remove all hydrogen from atom list bounded atoms
            List<String> temp;
            Iterator<String> it;

            for (int i = 0; i < atoms.size(); i++) {
                temp = atoms.get(i).boundedAtoms;
                it = temp.iterator();
                while (it.hasNext()) {
                    sb.append(it.next());
                    if (sb.toString().charAt(0) == ('H')) {
                        it.remove();
                    }
                    sb.delete(0, sb.length());
                }
            }

            // remove hydrogen from branches basic chain
            List<Integer> numbersWithHydrogen = new ArrayList<>();
            for (Map.Entry<Integer, Map<Integer, Map<Integer, String>>> entry : branchesAndAtomicIndexes.entrySet()) {
                int branchIndex = entry.getKey();
                Map<Integer, Map<Integer, String>> value = entry.getValue();
                for (Map.Entry<Integer, Map<Integer, String>> e : value.entrySet()) {
                    int number = e.getKey();
                    Map<Integer, String> value2 = e.getValue();
                    for (Map.Entry<Integer, String> e2 : value2.entrySet()) {
                        if (e2.getValue().charAt(0) == 'H') {
                            numbersWithHydrogen.add(branchIndex);
                            numbersWithHydrogen.add(number);
                        }
                    }
                }
            }

            for (int i = 0; i < numbersWithHydrogen.size(); i += 2) {
                branchesAndAtomicIndexes.get(numbersWithHydrogen.get(i)).remove(numbersWithHydrogen.get(i + 1));
            }

            // check for empty branches and remove them
            Iterator<Integer> br = branchesAndAtomicIndexes.keySet().iterator();
            while (br.hasNext()) {
                int branch = br.next();
                if (branchesAndAtomicIndexes.get(branch).isEmpty()) {
                    br.remove();
                }
            }
            System.out.println("Map after empty branches deleted:" + branchesAndAtomicIndexes);
            System.out.println("Atoms before which: " + atoms);
            // reorder atom list numbers
            List<Atom> reorderedAtomList = new ArrayList<>();
            // first string it was and next string it is now. For renumbering elements and changing bounded elements
            Map<String, String> whichNumberChangedToWhich = new LinkedHashMap<>();
            for (int i = 0; i < atoms.size(); i++) {
                reorderedAtomList.add(atoms.get(i));
                whichNumberChangedToWhich.put((atoms.get(i).element + atoms.get(i).id), atoms.get(i).element + (i + 1));
                reorderedAtomList.get(i).id = i + 1;
            }
            System.out.println("Reordered atom list without bounding reorder: " + reorderedAtomList);
            System.out.println("Which number is which: " + whichNumberChangedToWhich);
            atoms.clear();
            for (int i = 0; i < reorderedAtomList.size(); i++) {
                atoms.add(reorderedAtomList.get(i));
                middle:
                for (int j = 0; j < reorderedAtomList.get(i).boundedAtoms.size(); j++) {
                    atomSign.append(reorderedAtomList.get(i).boundedAtoms.get(j));
                    for (Map.Entry<String, String> entry : whichNumberChangedToWhich.entrySet()) {
                        if (entry.getKey().equals(atomSign.toString())) {
                            atoms.get(i).boundedAtoms.set(j, entry.getValue());
                            atomSign.delete(0, atomSign.length());
                            continue middle;
                        }
                    }
                }
            }
            System.out.println("Reordered atom list with bounding reorder: " + reorderedAtomList);

            // reorder branch
            Map<Integer, Map<Integer, Map<Integer, String>>> branchesAndAtomicIndexesReordered = new LinkedHashMap<>();
            reOrderBranch(whichNumberChangedToWhich, branchesAndAtomicIndexesReordered);
            branchesAndAtomicIndexes.clear();
            branchesAndAtomicIndexes.putAll(branchesAndAtomicIndexesReordered);
            branchesAndAtomicIndexesReordered.clear();
            System.out.println("Branches after reorder:" + branchesAndAtomicIndexes);
            mutatedToHydrogenAndBoundingElement.clear();
        }
        locked = false;

        // if no branch left
        if (branchesAndAtomicIndexes.isEmpty()) {
            throw new EmptyMolecule();
        }

        return this;
    }

    public void reOrderBranch(Map<String, String> which, Map<Integer, Map<Integer, Map<Integer, String>>> branchesAndAtomicIndexesReordered) {
        int counterForBranch = 0;
        int counterForNumber;
        int newNumber;
        StringBuilder newAtomNumber = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, Map<Integer, Map<Integer, String>>> entry : branchesAndAtomicIndexes.entrySet()) {
            counterForBranch++;
            counterForNumber = 0;
            branchesAndAtomicIndexesReordered.put(counterForBranch, new LinkedHashMap<>());
            Map<Integer, Map<Integer, String>> value = entry.getValue();
            for (Map.Entry<Integer, Map<Integer, String>> e : value.entrySet()) {
                counterForNumber++;
                branchesAndAtomicIndexesReordered.get(counterForBranch).put(counterForNumber, new LinkedHashMap<>());
                Map<Integer, String> value2 = e.getValue();
                for (Map.Entry<Integer, String> e2 : value2.entrySet()) {
                    sb.append(e2.getValue()).append(e2.getKey());
                    newAtomNumber.append(which.get(sb.toString()));
                    if (newAtomNumber.toString().matches("[A-Z][1-9]([0-9]+)?")) {
                        newNumber = Integer.parseInt(newAtomNumber.toString().substring(1));
                    } else {
                        newNumber = Integer.parseInt(newAtomNumber.toString().substring(2));
                    }
                    branchesAndAtomicIndexesReordered.get(counterForBranch).get(counterForNumber).put(newNumber, e2.getValue());
                    sb.delete(0, sb.length());
                    newAtomNumber.delete(0, newAtomNumber.length());
                }
            }
        }
    }

    public void listOfAtomsOrderingBoundedAtoms(int atomIndex) {
        List<String> result = new ArrayList<>();
        List<String> at = new ArrayList<>(Arrays.asList("C", "O", "B", "Br", "Cl", "F", "Mg", "N", "P", "S", "H"));
        int counter = 0;
        List<String> sameAtomsInOrder;
        do {
            sameAtomsInOrder = listOfAtomsBoundedAtomSearcher(atomIndex - 1, at.get(counter));
            result.addAll(sameAtomsInOrder);
            counter++;
        }
        while (counter < at.size());
        atoms.get(atomIndex - 1).boundedAtoms.clear();
        atoms.get(atomIndex - 1).boundedAtoms.addAll(result);
        result.clear();
    }

    public List<String> listOfAtomsBoundedAtomSearcher(int atom, String searchedAtom) {
        List<String> originalList = atoms.get(atom).boundedAtoms;
        List<String> searchedAtomsList = new ArrayList<>(); // list with searched atoms
        int atomStringLength = searchedAtom.length();
        if (searchedAtom.equals("H")) {
            for (int i = 0; i < originalList.size(); i++) {
                if (originalList.get(i).substring(0, 1).equals(searchedAtom) && originalList.get(i).matches("[A-Z]([1-9])?([0-9]+)?")) {
                    searchedAtomsList.add("H");
                }
            }
            return searchedAtomsList;
        } else {

            for (int i = 0; i < originalList.size(); i++) {
                if (atomStringLength == 1) {
                    if (originalList.get(i).substring(0, 1).equals(searchedAtom) && originalList.get(i).matches("[A-Z]([1-9])?([0-9]+)?")) {
                        searchedAtomsList.add(originalList.get(i));
                    }
                } else {
                    if (originalList.get(i).length() >= 2) {
                        if (originalList.get(i).substring(0, 2).equals(searchedAtom)) {
                            searchedAtomsList.add(originalList.get(i));
                        }
                    }
                }
            }
            // Ordering by numbers (example: C1 C4 C8 C15)
            List<Integer> numbers = new ArrayList<>();
            int nb;
            for (int i = 0; i < searchedAtomsList.size(); i++) {
                if (atomStringLength == 1) {

                    nb = Integer.parseInt(searchedAtomsList.get(i).substring(1));
                    numbers.add(nb);
                } else {
                    nb = Integer.parseInt(searchedAtomsList.get(i).substring(2));
                    numbers.add(nb);
                }
            }
            Collections.sort(numbers);
            for (int i = 0; i < numbers.size(); i++) {
                if (atomStringLength == 1) {
                    if (searchedAtom.equals("H")) {
                        searchedAtomsList.set(i, searchedAtom);
                    } else {
                        searchedAtomsList.set(i, searchedAtom + numbers.get(i));
                    }
                } else {
                    searchedAtomsList.set(i, searchedAtom + numbers.get(i));
                }
            }
            return searchedAtomsList;
        }
    }
// end of molecule class
}


class T {
    public int c1, c2, b1, b2, nc, nb;
    public String elt = "";

    public T(int a, int b, int c, int d) {
        c1 = a; b1 = b; c2 = c; b2 = d;
    }

    public T(int a, int b, String c) {
        nc = a; nb = b; elt = c;
    }

    @Override
    public String toString() {
        return elt.isEmpty() ? String.format("T(%d, %d, %d, %d)", c1, b1, c2, b2)
                : String.format("T(%d, %d, %s)", nc, nb, elt);
    }
}


class EmptyMolecule extends RuntimeException {
    public EmptyMolecule() {
    }
}

class InvalidBond extends RuntimeException {
    public InvalidBond() {
    }
}

class UnlockedMolecule extends RuntimeException {
    public UnlockedMolecule() {
    }
}

class LockedMolecule extends RuntimeException {
    public LockedMolecule() {
    }
}