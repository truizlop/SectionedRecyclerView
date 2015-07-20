/*
 * Copyright (C) 2015 Tomás Ruiz-López.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.truizlop.sectionedrecyclerviewsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.truizlop.sectionedrecyclerview.SimpleSectionedAdapter;
import com.truizlop.sectionedrecyclerviewsample.viewholders.AgendaItemViewHolder;


public class AgendaSimpleSectionAdapter extends SimpleSectionedAdapter<AgendaItemViewHolder>{

    @Override
    protected String getSectionHeaderTitle(int section) {
        return section == 0 ? "Today" : "Tomorrow";
    }

    @Override
    protected int getSectionCount() {
        return 2;
    }

    @Override
    protected int getItemCountForSection(int section) {
        return 3;
    }

    @Override
    protected AgendaItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_agenda_item, parent, false);
        return new AgendaItemViewHolder(view);
    }

    protected String[][] agenda = {{"Meeting", "Phone call", "Interview"},
            {"Basket match", "Grocery shopping", "Taking a nap"}};

    @Override
    protected void onBindItemViewHolder(AgendaItemViewHolder holder, int section, int position) {
        holder.render(agenda[section][position]);
    }
}
