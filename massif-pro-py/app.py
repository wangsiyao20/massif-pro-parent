from flask import Flask, render_template

app = Flask(__name__)


@app.route('/')
def hello_world():  # put application's code here
    return render_template('index.html')


@app.route('/register')
def register_view():
    return render_template("register.html")


@app.route('/login')
def log_view():
    return render_template('login.html')


if __name__ == '__main__':
    app.run()
