package com.reserva;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Gestion extends Activity {
    String id = "";
    String nombre = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion2);
    }

    public void eliminarUsuario(View v)
    {
        EditText Cnombre = (EditText) findViewById(R.id.txtNombre);
        TextView lblId = (TextView) findViewById(R.id.txtID);
        id = lblId.getText().toString();
        nombre = Cnombre.getText().toString();
        AuxiliarSQL sql = new AuxiliarSQL(this,"DB_Restaurant", null, 1);
        // Escribir en la base de datos
        final SQLiteDatabase db = sql.getWritableDatabase();
        if(db != null){
            try{
                db.execSQL("delete from Reservacion where _id = '"+id+"' and nombre = '"+nombre+"';");
                Toast.makeText(getApplicationContext(),
                        "Usuario eliminado.",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),
                        "Error al borrar en: " + e,
                        Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "No existe la base de datos",
                    Toast.LENGTH_LONG).show();
        }

    }

    public void editarUsuario (View v)
    {
        EditText Cnombre = (EditText) findViewById(R.id.txtNombre);
        TextView lblId = (TextView) findViewById(R.id.txtID);
        id = lblId.getText().toString();
        nombre = Cnombre.getText().toString();
        AuxiliarSQL sql = new AuxiliarSQL(this,"DB_Restaurant", null, 1);
        final SQLiteDatabase db = sql.getWritableDatabase();
        if(db != null){
            try{
                db.execSQL("update Reservacion set nombre = '"+nombre+"' where _id = '"+id+";");
                Toast.makeText(getApplicationContext(),
                        "Usuario actualizado.",
                        Toast.LENGTH_LONG).show();
            } catch (Exception e){
                Toast.makeText(getApplicationContext(),
                        "Error en: " + e,
                        Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(getApplicationContext(),
                    "No existe la base de datos",
                    Toast.LENGTH_LONG).show();
        }
    }



    public void regresar(View v)
    {
        Intent regresar = new Intent(this, MainActivity.class);
        finish();
        startActivity(regresar);
    }
}
