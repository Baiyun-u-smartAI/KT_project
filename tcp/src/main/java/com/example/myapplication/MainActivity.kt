package com.example.myapplication

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import java.io.*
import java.net.InetAddress
import java.net.ServerSocket
import java.net.Socket
import java.nio.ByteBuffer
import java.nio.ByteOrder


class MainActivity : AppCompatActivity() {
    lateinit var 接收端_服务器IP: EditText
    lateinit var 发送的消息: EditText
    lateinit var 本地IP显示: TextView
    lateinit var 服务器开关: Switch
    lateinit var 启动服务器的线程: Thread
    lateinit var 连接端口: EditText
    val IP正则: Regex = Regex(
        "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\."
                + "(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        接收端_服务器IP = findViewById(R.id.IP地址)
        发送的消息 = findViewById(R.id.发送内容)
        本地IP显示 = findViewById(R.id.本地IP)
        服务器开关 = findViewById(R.id.服务器开关)
        连接端口 = findViewById(R.id.端口)
        // 显示本地WIFI的IP地址
        var wm: WifiManager = this.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val wifiInfo: WifiInfo = wm.getConnectionInfo()
        val ipInt: Int = wifiInfo.getIpAddress()
        val IP地址: String = InetAddress.getByAddress(
            ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(ipInt).array()
        )
            .getHostAddress()

        本地IP显示.setText("本机IP:" + IP地址)
        启动服务器的线程 = Thread(Socket服务器对象(this))

    }


    class Socket服务器对象(val Toast显示Context: Context) : Runnable {
        lateinit var Socket服务器: ServerSocket
        var Socket通讯对象: Socket? = null
        var 数据发送输入流: DataInputStream? = null
        var 消息: String? = null
        val 提示消息控制: Handler = Handler()
        val context: Context = Toast显示Context

        override fun run() {

            try {
                Socket服务器 = ServerSocket(9700)
                提示消息控制.post {
                    Toast.makeText(context, "server run successfully", Toast.LENGTH_SHORT).show()
                }


                while (!Thread.currentThread().isInterrupted()) {
                    Socket通讯对象 = Socket服务器.accept()
                    数据发送输入流 = DataInputStream(Socket通讯对象!!.getInputStream())
                    Socket通讯对象!!.localAddress
                    消息 = 数据发送输入流!!.readUTF()
                    提示消息控制.post {
                        Toast.makeText(context, "receve from client:" + 消息!!, Toast.LENGTH_SHORT).show()

                    }

                }
            } catch (通讯异常: IOException) {
                通讯异常.printStackTrace()
            }
        }
    }

    public fun 启动服务器(v: View) {
        if (服务器开关.isChecked) {
            启动服务器的线程.start()

        } else {
            // 重启应用

            intent?.putExtra("REBOOT", "reboot")
            val pendingIntent = PendingIntent.getActivity(
                this.applicationContext,
                0,
                this.baseContext.packageManager.getLaunchIntentForPackage(this.baseContext.packageName),
                PendingIntent.FLAG_ONE_SHOT
            )
            val alarmManager = this.applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
// 1秒后启动
            alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + 200, pendingIntent)
            android.os.Process.killProcess(android.os.Process.myPid())
        }

    }


    public fun 发送按钮点击函数(v: View) {
        val 客户端发送消息的任务: BackgroundTask = BackgroundTask()
        if (IP正则.matches(接收端_服务器IP.text.toString()) && !(发送的消息.text.isBlank()) ){
            lateinit var 端口:String
            if (!连接端口.text.isBlank()){
                端口= 连接端口.text.toString()
            }else{
                端口="9700"
            }

            客户端发送消息的任务.execute(接收端_服务器IP.text.toString(), 发送的消息.text.toString(), 端口)
        }

        else {
            val 提示消息控制: Handler = Handler()
            提示消息控制.post {
                Toast.makeText(this, "sending fail: invalid", Toast.LENGTH_SHORT).show()

            }
        }
    }


    class BackgroundTask : AsyncTask<String, Void, String>() {
        var Socket通讯对象: Socket? = null
        var 数据输出流: DataOutputStream? = null
        var IP地址: String? = null
        var 消息: String? = null
        var 端口: Int? = null
        override fun doInBackground(vararg params: String?): String? {
            // 接收端_服务器IP --->params[0]   发送的消息--->params[1]
            IP地址 = params[0]!!
            消息 = params[1]!!
            端口 = params[2]!!.toInt()
            try {
                Socket通讯对象 = Socket(IP地址!!, 端口!!)
                数据输出流 = DataOutputStream(Socket通讯对象!!.getOutputStream())
                数据输出流!!.writeUTF(消息!!)
                数据输出流!!.close()
                Socket通讯对象!!.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

            return null
        }
    }
}
