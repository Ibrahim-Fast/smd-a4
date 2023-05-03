from flask import Flask,jsonify


app = Flask(__name__)

@app.route('/categories')
def give_categories():
    return jsonify([
        {"title":"Pizza", "pic":"cat_1"},
        {"title":"Burger", "pic":"cat_2"},
        {"title":"HotDog", "pic":"cat_3"},
        {"title":"Drink", "pic":"cat_4"},
        {"title":"Donut", "pic":"cat_5"}
    ])


@app.route('/popular')
def give_popular():
    return jsonify([
        {"title":"Fajita pizza", "pic":"pop_1","description":"chicken chunks, mozeralla cheese, cheddar cheese, special sauce, special spices","fee":"850.90"},
        {"title":"Beef cheese burger", "pic":"pop_2","description":"beef , cheese, sauce, coleslaw","fee":"330.90"},
        {"title":"Zinger cheese burger", "pic":"pop_2","description":"crispy chicken, cheese, sauce, coleslaw","fee":"330.90"}
    ])

if __name__ == '__main__':
    app.run(port=3002)
