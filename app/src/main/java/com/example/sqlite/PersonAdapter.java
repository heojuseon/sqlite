package com.example.sqlite;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private ArrayList<Person> person_list = new ArrayList<>();
    private Context context;

    DBHelper dbHelper;

    int id;

    public PersonAdapter(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context.getApplicationContext());
    }

    @NonNull
    @Override
    public PersonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Person person = person_list.get(position);

        holder.item_name.setText(person.getPname());
        holder.item_phone.setText(person.getPphone());
        holder.icon.setImageResource(R.drawable.id);


        //popupmenu
        holder.icon2.setImageResource(R.drawable.setting);
        holder.icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "클릭", Toast.LENGTH_SHORT).show();

                PopupMenu popupMenu = new PopupMenu(context, holder.icon2);
                popupMenu.inflate(R.menu.detail_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

                    @SuppressLint("UnsafeIntentLaunch")
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getItemId() == R.id.delete){
                            Toast.makeText(context.getApplicationContext(), "삭제 버튼 클릭", Toast.LENGTH_SHORT).show();

                            //삭제 기능
                            id = person_list.get(position).getId();
                            person_list.remove(position);
                            dbHelper.deletePerson(id);

                            //삭제시 리스트 새로고침
                            Intent intent = ((Activity)context).getIntent();
                            ((Activity)context).finish();   //현재 액티비티 종료 실시
                            ((Activity)context).overridePendingTransition(0, 0);    //효과 없애기
                            ((Activity)context).startActivity(intent);
                            ((Activity)context).overridePendingTransition(0, 0);
                        }
                        else {
                            Toast.makeText(context.getApplicationContext(), "수정 버튼 클릭", Toast.LENGTH_SHORT).show();
                            //intent 를 통해 입력받은 데이터 전달
                            Intent intent = new Intent(context.getApplicationContext(), UpdatePerson.class);
                            intent.putExtra("id", person.getId());
                            intent.putExtra("name", person.getPname());
                            intent.putExtra("phone", person.getPphone());
                            context.startActivity(intent);
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return person_list.size();
    }

    public void addItem(Person person) {
        person_list.add(person);
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView item_name, item_phone;
        private ImageView icon;
        private ImageButton icon2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.list_name);
            item_phone = itemView.findViewById(R.id.list_phone_number);
            icon = itemView.findViewById(R.id.icon);
            icon2 = itemView.findViewById(R.id.set_btn);
        }
    }
}
