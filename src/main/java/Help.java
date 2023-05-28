public class Help {
    private String message;

    public void Help() {
        message = "To jest pomocy programu Tiemloger:" +
                "start -p <nazwa projektu> -t <nazwa zadania> -rozpoczęcie nowego zadania," +
                "stop - zakończenie bieżącego zadania," +
                "last + n - wyświetlanie n ostatnich logów wraz z numerami indeksów," +
                "list - wyświetlanie wszystkich logów," +
                "report - generowanie raportu.";
    }
    public void displayHelpMessage() {
        System.out.println(message);
    }
}
