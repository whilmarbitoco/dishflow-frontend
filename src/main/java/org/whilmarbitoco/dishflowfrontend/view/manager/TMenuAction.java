package org.whilmarbitoco.dishflowfrontend.view.manager;

import org.whilmarbitoco.dishflowfrontend.model.Menu;

public interface TMenuAction {

    void onDelete(Menu menu);
    void onEdit(Menu menu);
    void onView(Menu menu);

}
