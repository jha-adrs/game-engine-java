package aadarsh;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClearColor;
import static org.lwjgl.opengl.GL11.glClear;

import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private String title;
    private int width, height;
    private static Window window = null;
    private long glfwWindow; //Stores the memory address where window is stored

    private Window() {
        // To ensure outsiders can't create this instance
        this.width = 1920;
        this.height = 1080;
        this.title = "My Mario Clone";
    }

    public static Window get() {
        //Returns the Window
        if (Window.window == null) {
            Window.window = new Window();

        }
        return Window.window;
    }

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();
    }

    public void loop() {
        while (!glfwWindowShouldClose((glfwWindow))) { // While the window should not close, keep looping
            // Poll for events
            glfwPollEvents();
            glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);

            glfwSwapBuffers(glfwWindow); // Swap the front and back buffers

        }
    }

    public void init() {
        //Error callback set to the terminal
        GLFWErrorCallback.createPrint(System.err).set();
        // Init GLFW
        if (!glfwInit()) {
            throw new IllegalStateException("Unable to initialaize GLFW");
        }
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        //Create the window

        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }
        // Make the OpenGL Context Current
        glfwMakeContextCurrent(glfwWindow);
        //Enable v-sync
        glfwSwapInterval(1); // 0 for no v-sync and 1 for v-sync

        // Make the window visible now
        glfwShowWindow(glfwWindow);

        GL.createCapabilities(); // VVI: This is important to make the OpenGL context current
    }
}
