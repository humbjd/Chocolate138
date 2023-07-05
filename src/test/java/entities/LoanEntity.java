package entities;

public class LoanEntity {
    public String userId;
    public ISBN[] collectionOfIsbns; // lista com os códigos dos livros emprestados

    public String isbn; // código do livro

    public static class ISBN{
        private final String isbn; // codigo do livro interno

        public ISBN(String isbn){          // Construtor
            this.isbn = isbn;
        }
    }


}
