⚠ This library is no longer maintained ⚠️

# SectionedRecyclerView [ ![Download](https://api.bintray.com/packages/truizlop/maven/sectionedrecyclerview/images/download.svg) ](https://bintray.com/truizlop/maven/sectionedrecyclerview/_latestVersion) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SectionedRecyclerView-green.svg?style=flat)](https://android-arsenal.com/details/1/2165)
An adapter to create Android RecyclerViews with sections, providing headers and footers.

## Usage

In order to use this library, you need to extend `SectionedRecyclerView<H, VH, F>` where:

- `H` is a class extending `RecyclerView.ViewHolder` to hold the view for section **headers**.
- `VH` is a class extending `RecyclerView.ViewHolder` to hold the view for the regular **items** in the view.
- `F` is a class extending `RecyclerView.ViewHolder` to hold the view for section **footers**.

According to the sample published in this repository:

- 1. Create a class extending `SectionedRecyclerView`:

```java
public class CountSectionAdapter extends SectionedRecyclerViewAdapter<CountHeaderViewHolder,
        CountItemViewHolder,
        CountFooterViewHolder>
```

- 2. Implement the corresponding methods:

```java
@Override
protected int getItemCountForSection(int section) {
    return section + 1;
}

@Override
protected int getSectionCount() {
    return 5;
}

@Override
protected boolean hasFooterInSection(int section) {
    return true;
}

protected LayoutInflater getLayoutInflater(){
    return LayoutInflater.from(context);
}

@Override
protected CountHeaderViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
    View view = getLayoutInflater().inflate(R.layout.view_count_header, parent, false);
    return new CountHeaderViewHolder(view);
}

@Override
protected CountFooterViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
    View view = getLayoutInflater().inflate(R.layout.view_count_footer, parent, false);
    return new CountFooterViewHolder(view);
}

@Override
protected CountItemViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
    View view = getLayoutInflater().inflate(R.layout.view_count_item, parent, false);
    return new CountItemViewHolder(view);
}

@Override
protected void onBindSectionHeaderViewHolder(CountHeaderViewHolder holder, int section) {
    holder.render("Section " + (section + 1));
}

@Override
protected void onBindSectionFooterViewHolder(CountFooterViewHolder holder, int section) {
    holder.render("Footer " + (section + 1));
}

protected int[] colors = new int[]{0xfff44336, 0xff2196f3, 0xff009688, 0xff8bc34a, 0xffff9800};
@Override
protected void onBindItemViewHolder(CountItemViewHolder holder, int section, int position) {
    holder.render(String.valueOf(position + 1), colors[section]);
}
```

- 3. If you use a `GridLayoutManager`, you need to set it a `SectionedSpanSizeLookup` to make sure that headers and footers span the whole width of the `RecyclerView`:

```java
GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
SectionedSpanSizeLookup lookup = new SectionedSpanSizeLookup(adapter, layoutManager);
layoutManager.setSpanSizeLookup(lookup);
recycler.setLayoutManager(layoutManager);
```

- 4. Your result will look like this:

![SectionedRecyclerView screenshot][1]

## Even simpler

Most times you will need a simpler version of this adapter, where there are no footers and your headers will only be a title. For those cases, you have `SimpleSectionedAdapter<VH>`, where `VH` is a class extending `ViewHolder` to hold the view of the regular items in your `RecyclerView`.

In this case, you will have to implement the following methods:

```java
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
```

Your result will look like this:

![SimpleSectionedAdapter screenshot][2]

## Get it!

`SectionedRecyclerView` is available through JCenter. To be able to use this library in your project, add the following dependency to your `build.gradle` file:

```groovy
dependencies{
	compile 'com.truizlop.sectionedrecyclerview:library:1.2.0'
}
```

## License


    Copyright 2015 Tomás Ruiz-López

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: ./art/screenshot1.png
[2]: ./art/screenshot2.png
