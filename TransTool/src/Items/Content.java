/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import FixHTML.FixHTML;

/**
 *
 * @author hallm8
 */
public class Content extends Item {

    public Content() {

    }

    @Override
    public void writeItem() {
        FixHTML fix = new FixHTML();
        fix.setItem(this);
        fix.setFilePath(savePath);
        fix.fix();
        fix.writeHTML();
    }
}
