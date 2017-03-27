package demo;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by marwane on 27/03/17.
 */

public interface ClientRepo extends JpaRepository<Client, Long> {


}