package widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhimei.can_delete_listview.R;

import java.util.List;

import utitls.Chatter;

/**
 * Created by 张佳亮 on 2016/2/27.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Chatter> datas;
    private LayoutInflater inflater;

    public MyAdapter(Context context, List<Chatter> datas) {
        this.context = context;
        this.datas = datas;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return datas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View covertView, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(covertView==null){
            holder=new ViewHolder();
            covertView=inflater.inflate(R.layout.item_list,null);
            holder.imageView=(ImageView)covertView.findViewById(R.id.image);
            holder.type=(TextView)covertView.findViewById(R.id.type);
            holder.content=(TextView)covertView.findViewById(R.id.content);
            holder.time=(TextView)covertView.findViewById(R.id.time);
            holder.zhhiding=(TextView)covertView.findViewById(R.id.zhiding);
            holder.biaojiweiyidu=(TextView)covertView.findViewById(R.id.biaojiweiyidu);
            holder.delete=(TextView)covertView.findViewById(R.id.delete);

            covertView.setTag(holder);
        }
        else {
            holder=(ViewHolder)covertView.getTag();
        }

        holder.imageView.setImageBitmap(datas.get(position).getPicture());
        holder.type.setText(datas.get(position).getType());
        holder.content.setText(datas.get(position).getContent());
        holder.time.setText(datas.get(position).getTime());



        holder.biaojiweiyidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "标记为已读", Toast.LENGTH_SHORT).show();
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"已经删除",Toast.LENGTH_SHORT).show();
            }
        });


        holder.zhhiding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "已经置顶", Toast.LENGTH_SHORT).show();
            }
        });







        return covertView;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView type,content,time,zhhiding,biaojiweiyidu,delete;

    }
}
