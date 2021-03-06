package wpk.account;
import java.util.Objects;
import wpk.Enum.AccountStatus;

/**
 *
 * @author Sathipp
 */
public abstract class Account {
    private String id,firstname,lastname;
    private String password;
    private long phone;
    private AccountStatus status ;

    public Account(String id, String firstname, String lastname, String password, long phone, AccountStatus status) {
        this.id=id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public long getPhone() {
        return phone;
    }

    public AccountStatus getStatus() {
        return status;
    }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (this.phone != other.phone) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        return true;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Account id: " + id + ", Name" + firstname + "  " + lastname + ", password=" + password + ", phone=" + phone + ", status=" + status ;
    }
   
    
    
    
   
}
