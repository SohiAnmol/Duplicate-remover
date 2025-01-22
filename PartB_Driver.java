/**
 * Anmoldeep Singh
 * 3149800
 */
public class PartB_Driver {
    public static ArrayPositionalList<String> removeConsecutiveDuplicates(ArrayPositionalList<String> list) {
        Position<String> current = list.first();
        while (current != null) {
            Position<String> next = list.after(current);
            if (next != null && current.getElement().equals(next.getElement())) {
                list.remove(next);
            } else {
                current = next;
            }
        }

        return list;
    }

    public static void displayArray(ArrayPositionalList<String> apl) {
        Position<String> currentPos = apl.first();
        int ind = 0;
        while (currentPos != null) {
            System.out.print("[" + ind + "] " + currentPos.getElement() + " ");
            currentPos = apl.after(currentPos);
            ind++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayPositionalList<String> list = new ArrayPositionalList<>();
        list.addLast("mary");
        list.addLast("mary");
        list.addLast("tom");
        list.addLast("james");
        list.addLast("hermione");
        list.addLast("hermione");
        list.addLast("james");
        list.addLast("harry");
        list.addLast("harry");
        list.addLast("harry");

        System.out.println("Original positional List:");
        displayArray(list);

        ArrayPositionalList<String> ArrayListAfterRemoved = removeConsecutiveDuplicates(list);

        System.out.println("\nNumber of entries after call: " + ArrayListAfterRemoved.size());
        System.out.println("List with duplicates removed: ");
        displayArray(ArrayListAfterRemoved);

    }
}
