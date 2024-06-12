package sia.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sia.domain.CDPlayer;
import sia.domain.interfaces.CompactDisc;
import sia.domain.SgtPeppers;

@Configuration
public class CDPlayerConfig {
  
  @Bean(name="pepper")
  public CompactDisc compactDisc() {
    return new SgtPeppers();
  }
  
  @Bean
  public CDPlayer cdPlayer(CompactDisc compactDisc) {
    return new CDPlayer(compactDisc);
  }

//  @Bean
//  public CDPlayer cdPlayer2() {
//    return new CDPlayer(compactDisc());
//  }
}
