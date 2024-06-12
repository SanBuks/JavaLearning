package sia.domain;
import org.springframework.beans.factory.annotation.Autowired;
import sia.domain.interfaces.CompactDisc;
import sia.domain.interfaces.MediaPlayer;

public class CDPlayer implements MediaPlayer {
  private CompactDisc cd;

  @Autowired
  public CDPlayer(CompactDisc cd) {
    this.cd = cd;
  }

  public void play() {
    cd.play();
  }

}
