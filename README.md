# adapter
recyclerview 配套使用adapter

使用方式：
rv.setAdapter(cellAdapter);
        List<Cell> cells = new ArrayList<>();
        for (String 对象 : list) {
            cells.add(MultiCell.convert(你的布局ID, 对象, new DataBinder<对象>() {
                @Override
                public void bindData(RVViewHolder var1, String var2) {

                }
            }));
        }
        cellAdapter.setDataList(cells);
