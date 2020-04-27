package wpk.account;

import java.time.LocalDate;
import wpk.Movie.MovieBorrowing;
import wpk.account.Account;
import wpk.serviceITF.Specifications;
import wpk.Enum.AccountStatus;
import wpk.Enum.AccountMovieStatus;

/**
 *
 * @author Sathipp
 */
public class MemberAccount extends Account {

    private MovieBorrowing borrowingMovie[];
    private int totalBorrowMovie = 0;
    private int overDue = 0;
    private AccountMovieStatus memberMovieStatus;
    private AccountStatus status;

    public MemberAccount(String id, String firstname, String lastname, String password, long phone, AccountStatus status, AccountMovieStatus acstatus) {
        super(id, firstname, lastname, password, phone, status);
        this.setMaxBorrowMovie(status);
        this.setMemberMovieStatus(acstatus);
        this.status = status;
    }

    public void editDataMember(String firstname, String lastname, String password, long phone, AccountStatus status, AccountMovieStatus acstatus) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setPassword(password);
        this.setPhone(phone);
        this.setStatus(status);
        this.setMemberMovieStatus(acstatus);

    }

    @Override
    public String toString() {
        return super.toString() + " AccountMovieStatus " + memberMovieStatus;
    }

    public void setMaxBorrowMovie(AccountStatus status) {

        if (status.MEMBER.equals(AccountStatus.MEMBER)) {
            this.borrowingMovie = new MovieBorrowing[Specifications.MAX_BORROWMOVIE_MEMBER];
        } else if (status.PREMIUMMEMBER.equals(AccountStatus.PREMIUMMEMBER)) {
            this.borrowingMovie = new MovieBorrowing[Specifications.MAX_BORROWMOVIE_PREMIUMMEMBER];

        }
        if (AccountStatus.MEMBER != status || AccountStatus.PREMIUMMEMBER != status) {
            System.out.println("failed to create member account");
        }

    }

    public AccountMovieStatus getAccountMovieStatus() {
        return memberMovieStatus;
    }

    public void setMemberMovieStatus(AccountMovieStatus memberMovieStatus) {
        this.memberMovieStatus = memberMovieStatus;
    }

    public AccountStatus getMemberStatus() {
        return status;
    }

    public int getTotalMoviecheckedOut() {
        return totalBorrowMovie;
    }

    public String getMovieBorrowList() {

        System.out.println("Total: " + totalBorrowMovie);
        if (totalBorrowMovie > 0) {

            for (int i = 0; i < totalBorrowMovie; i++) {

                System.out.println("Movies: " + borrowingMovie[i].toString());
                return borrowingMovie[i].toString();

            }
        }
        return null;
    }

    public String ReturnIO() {
        if (totalBorrowMovie > 0) {

            for (int i = 0; i < totalBorrowMovie; i++) {

                System.out.println(borrowingMovie[i].toString());
                return borrowingMovie[i].toString();

            }
        }
        return "no borrow movie";
    }

    public boolean checkoutMovie(MovieBorrowing borrowMovie) {
        if (borrowMovie == null) {
            return false;
        }
        borrowingMovie[totalBorrowMovie++] = borrowMovie;

        return true;

    }

    int check(MovieBorrowing movie) {
        for (int i = 0; i < totalBorrowMovie; i++) {
            if (borrowingMovie[i].equals(movie)) {
                return i;
            }
        }
        return -1;
    }

    void remove(int index) {
        for (int i = index; i < totalBorrowMovie; i++) {
            if (i == totalBorrowMovie - 1) {
                for (int j = i + 1; j < totalBorrowMovie; j++) {
                    borrowingMovie[i] = borrowingMovie[j];
                }
                borrowingMovie[--totalBorrowMovie] = null;
            }

        }
    }

    public void returnMovie(MovieBorrowing borrowingMovie, int fine) {
        int returnMovie = check(borrowingMovie);
        if (returnMovie >= 0) {
            remove(returnMovie);
            if (fine > 0) {
                this.overDue++;
            }
        }
    }

    public int checkForFine(MovieBorrowing borrowedMovie) {
        return borrowedMovie.getFine(LocalDate.now());
    }

    public boolean checkstatus(Account account) {
        if (account.getStatus().equals(AccountStatus.MEMBER) || account.getStatus().equals(AccountStatus.PREMIUMMEMBER)) {
            return true;
        }
        return false;
    }

}
