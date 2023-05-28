public class Help {
    private String message = "To jest pomocy programu Tiemloger:\n" +
                "   - start -p <nazwa projektu> -t <nazwa zadania> -rozpoczęcie nowego zadania,\n" +
                "   - stop - zakończenie bieżącego zadania,\n" +
                "   - continue + n - rozpoczęcie zadania o indeksie n,\n" +
                "   - last + n - wyświetlanie n ostatnich logów wraz z numerami indeksów,\n" +
                "   - last  - wyświetlanie ostatniego loga,\n" +
                "   - list - wyświetlanie wszystkich logów,\n" +
                "   - report - generowanie raportu";

    public void displayHelpMessage() {
        System.out.println(message);
    }
}