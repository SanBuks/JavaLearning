package sia.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sia.domain.CDPlayer;
import sia.domain.interfaces.CompactDisc;

@Configuration
public class CDPlayerConfig {
  
  @Bean
  public CDPlayer cdPlayer(CompactDisc compactDisc) {
    return new CDPlayer(compactDisc);
  }

}
