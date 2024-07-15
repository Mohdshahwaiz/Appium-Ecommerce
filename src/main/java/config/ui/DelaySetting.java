

package config.ui;

import lombok.Data;

/**
 * UI Action delay settings.
 *
 * @author Rahul Rana
 * @since 04-Apr-2024
 */
@Data
public class DelaySetting {
    private int beforeClick;
    private int beforeMouseMove;
    private int beforeSwipe = 500;
    private int beforeTap;
    private int beforeTyping;
}
