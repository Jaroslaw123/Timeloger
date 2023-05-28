public class Help {
    private String message;

    public void Help() {
        message = "To jest pomocy programu Tiemloger:" +
                "-start -p <nazwa projektu> -t <nazwa zadania> -rozpoczęcie nowego zadania," +
                "-stop - zakończenie bieżącego zadania," +
                "-continue + n - rozpoczęcie zadania o indeksie n," +
                "-last + n - wyświetlanie n ostatnich logów wraz z numerami indeksów," +
                "-list - wyświetlanie wszystkich logów," +
                "-report data/data - generowanie raportu z wybranych dat.";
    }
    public void displayHelpMessage() {
        System.out.println(message);
    }
}
