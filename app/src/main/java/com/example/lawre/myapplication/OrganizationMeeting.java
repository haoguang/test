package com.example.lawre.myapplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.provider.SyncStateContract;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.lawre.myapplication.entity.Meeting;
import com.example.lawre.myapplication.others.IdGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrganizationMeeting extends Fragment {

    private static final int SEVEN_DAYS = 604800000;
    private  Context mcont;
    private static final String KK="MeetingData";

    private static final String TAG = "Lab-UserInterface";

    public static String timeString;
    public static String dateString;
    public static TextView dateView;
    public static TextView timeView;
    private OrganizationMeeting activity;


    private Date mDate;
    private EditText mTitleText;
    private EditText mObject;
    private EditText mLocation;
    private Spinner mInviter;
    String titleString ;
    String ObjectString ;
    String locationString ;
    GregorianCalendar time;
    GregorianCalendar date;
    String generatedid ;



    private String UPLOAD_URL = "http://meetsapp.chinfinitecarworld.com/organize.php";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");


    public OrganizationMeeting() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment


        final View view =  inflater.inflate(R.layout.fragment_organization_meeting, container, false);

        mTitleText = (EditText) view.findViewById(R.id.edit_Name);
        mObject = (EditText) view.findViewById(R.id.edit_object);
        mLocation = (EditText) view.findViewById(R.id.Enter_location);
        dateView = (TextView) view.findViewById(R.id.date);
        timeView = (TextView) view.findViewById(R.id.time);




        // Set the default date and time

        setDefaultDateTime();

        // OnClickListener for the Date button, calls showDatePickerDialog() to
        // show
        // the Date dialog

        final Button datePickerButton = (Button) view.findViewById(R.id.Picker_datebutton);
        datePickerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // OnClickListener for the Time button, calls showTimePickerDialog() to
        // show the Time Dialog

        final Button timePickerButton = (Button) view.findViewById(R.id.choose_timer);
        timePickerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });

        // OnClickListener for the Cancel Button,

        final Button cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i(TAG, "Entered cancelButton.OnClickListener.onClick()");

                // TODO - Indicate result and finish


                BackPrevious(v);
            }
        });



        // Set up OnClickListener for the Submit Button

        final Button submitButton = (Button) view.findViewById(R.id.submit_button);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered submitButton.OnClickListener.onClick()");

                // gather ToDoItem data


                try {
                    // TODO - Get the current ToDoItem Title
                    titleString = mTitleText.getText().toString();
                    ObjectString = mObject.getText().toString();
                    locationString = mLocation.getText().toString();

                    generatedid = Meeting.getNewID();



                    if (!titleString.trim().equalsIgnoreCase("") || !ObjectString.trim().equalsIgnoreCase("")
                            || !locationString.trim().equalsIgnoreCase("")

                            ) {
                        // TODO - Get the current Priority
                        // Spinner inviter = getInvited();


                        // Construct the Date string
                      //  String fullDate = dateString + " " + timeString;



                        // Package ToDoItem data into an Intent



                       // Meeting meeting = new Meeting(generatedid, titleString,date,time, locationString, ObjectString);

                        //Intent data = new Intent(getContext(),UpcomingMeeting.class);
                        //Bundle b = new Bundle();
                        //b.putParcelable(OrganizationMeeting.KK,meeting);
                       // data.putExtra("gen",generatedid);
                       // data.putExtra("title",titleString);
                       // data.putExtra("date",date );
                        //data.putExtra("time",time);
                        //data.putExtra("location",locationString);
                       // data.putExtra("object",ObjectString);

                        uploadImage();
                        //startActivity();


                        //TODO - return data Intent and finish

                        // Set Activity's result with result code RESULT_OK
                        //getActivity().setResult(RESULT_OK);
                        //getActivity().finish();

                    } else {
                        // Finish the Activity
                        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                        builder.setTitle("No title");
                        builder.setMessage("No title found !");
                        builder.setCancelable(true);
//				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//					@Override
//					public void onClick(DialogInterface dialog, int which) {
//						mItems.remove(pos);
//						notifyDataSetChanged();
//					}
//				});
                        builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }catch (Exception e){

                }


            }
        });


        return view;



    }


    // protected void resetToDefault() {
    //setDefaultDateTime();

    // mTitleText.setText("");

    // mPriorityRadioGroup.check(R.id.medPriority);
    // mStatusRadioGroup.check(R.id.statusNotDone);
    // }

    // Do not modify below this point.

    public void BackPrevious(View v){

        Fragment newFragment = new UpcomingMeeting();
        // consider using Java coding conventions (upper first char class names!!!)
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.relative_layout_fragment, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();


    }


    private void setDefaultDateTime() {

        // Default is current time + 7 days
        mDate = new Date();
        mDate = new Date(mDate.getTime() + SEVEN_DAYS);


        Calendar c = Calendar.getInstance();
        c.setTime(mDate);

        setDateString(c.get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH));


        dateView.setText(dateString);


        setTimeString(c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE),
                c.get(Calendar.MILLISECOND));


        timeView.setText(timeString);
    }

    private static void setDateString(int year, int monthOfYear, int dayOfMonth) {

        // Increment monthOfYear for Calendar/Date -> Time Format setting
        monthOfYear++;
        String mon = "" + monthOfYear;
        String day = "" + dayOfMonth;

        if (monthOfYear < 10)
            mon = "0" + monthOfYear;
        if (dayOfMonth < 10)
            day = "0" + dayOfMonth;

        dateString = year + "-" + mon + "-" + day;
    }

    private static void setTimeString(int hourOfDay, int minute, int mili) {
        String hour = "" + hourOfDay;
        String min = "" + minute;

        if (hourOfDay < 10)
            hour = "0" + hourOfDay;
        if (minute < 10)
            min = "0" + minute;

        timeString = hour + ":" + min + ":00";

    }

    //private Spinner getInvited() {



    //}

    //  private Status getStatus() {

    //  switch (mStatusRadioGroup.getCheckedRadioButtonId()) {
    //  case R.id.statusDone: {
    //  return Status.DONE;
    //}
    //  default: {
    //   return Status.NOTDONE;
    // }
    // }
    //   }

    private String getToDoTitle() {
        return mTitleText.getText().toString();
    }


    // DialogFragment used to pick a ToDoItem deadline date
    public static class DatePickerFragment extends DialogFragment implements
           DatePickerDialog.OnDateSetListener {

    @Override
       public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current date as the default date in the picker

            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            setDateString(year, monthOfYear, dayOfMonth);
            if(dateView!=null) {
               dateView.setText(dateString);
            }else{


            }
        }

    }

    // DialogFragment used to pick a ToDoItem deadline time

    public static class TimePickerFragment extends DialogFragment implements
            TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return
            return new TimePickerDialog(getActivity(), this, hour, minute, true);
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            setTimeString(hourOfDay, minute, 0);

            timeView.setText(timeString);
        }
    }

    private void showDatePickerDialog() {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

   private void showTimePickerDialog() {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getFragmentManager(), "timePicker");
   }


    protected void uploadImage(){
        //show the progress dialog
        // Toast.makeText(createEvent.this, "I am ThomasLaw", Toast.LENGTH_SHORT).show();
        final ProgressDialog loading = ProgressDialog.show(getActivity(), "Uploading...", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, UPLOAD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //Toast.makeText(createEvent.this, s+"Thomas Law loading..."+ssdf.format(startDate.getTime()), Toast.LENGTH_SHORT).show();
                //Dimissing the progress dialog
                loading.getProgress();
                loading.dismiss();
                //Showing toast meassage
                Toast.makeText(getActivity(), s+"\nSuccessful! ...\nClick back to return previous screen", Toast.LENGTH_SHORT).show();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Dismiss the progress dialog
                loading.dismiss();
                //Showing toast
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                //Converting bitmap to string

                //Creating parameters
                Map<String, String> params = new Hashtable< String,String>();


                try{
                  date = new GregorianCalendar(Calendar.YEAR,Calendar.MONTH,Calendar.DAY_OF_MONTH);
                  date.setTime(dateFormat.parse(dateString));

              }catch (ParseException e){
                  e.printStackTrace();
                }

              try {
                   time = new GregorianCalendar();
                   time.setTime(timeFormat.parse(timeString));
               }catch(ParseException e){
                   e.printStackTrace();
               }



                //Adding parameters
                params.put("MeetingID",generatedid);
                params.put("Title",titleString);
                params.put("Date",dateFormat.format(date.getTime()));
                params.put("Time",timeFormat.format(time.getTime()));
                params.put("Objective",ObjectString);
                params.put("Location",locationString);
               // params.put("Date",date);
               // params.put("Time",time);

                return params;
            }
        };

        //Creating request
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        //Adding request to queue
        requestQueue.add(stringRequest);

    }



}
