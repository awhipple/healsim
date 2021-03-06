package net.awhipple.zombiebird.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import net.awhipple.zombiebird.ZBGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(ZBGame.SCREEN_W, ZBGame.SCREEN_H);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new ZBGame();
        }
}