import json
import requests
from bs4 import BeautifulSoup
import time


def get_document(store_id):
    time.sleep(0.1)
    print(store_id)
    url = 'https://www.hollys.co.kr/store/korea/korStoreDetail2.do?branchId={}'.format(store_id)
    html = requests.get(url)
    soup = BeautifulSoup(html.text, 'html.parser')
    store_names = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > p')
    addresses = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(1) > span')
    directions = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(2) > span')
    contact_numbers = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(3) > span')
    opening_hours = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(4) > span')
    drive_through_icon = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(5) > span > img[alt="DT 매장"]')
    terrace_icon = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(5) > span > img[alt="테라스"]')
    twenty_four_hour_icon = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(5) > span > img[alt="24 시간"]')
    smoking_area_icon = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(5) > span > img[alt="훕연구역"]')
    parking_icon = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(5) > span > img[alt="주차"]')
    parking_fee_info = soup.css.select('#contents > div.content > div.store_view01 > div.store_detail02 > ul > li:nth-child(6) > span')
    store_images = soup.css.select('#contents > div.content > div.store_view01 > div.store_map_pic > img > ul > li > a > img')

    result = {'name': store_names[0].text.strip() if len(store_names) > 0 else '',
              'address': addresses[0].text.strip() if len(addresses) > 0 else '',
              'directions': directions[0].text.strip() if len(directions) > 0 else '',
              'contactNumber': contact_numbers[0].text.strip() if len(contact_numbers) > 0 else '',
              'openingHours': opening_hours[0].text.strip() if len(opening_hours) > 0 else '',
              'hasDriveThrough': len(drive_through_icon) > 0,
              'hasTerrace': len(terrace_icon) > 0,
              'has24Hour': len(twenty_four_hour_icon) > 0,
              'hasSmokingArea': len(smoking_area_icon) > 0,
              'hasParkingArea': len(parking_icon) > 0,
              'parkingFeeInfo': parking_fee_info[0].text.strip() if len(parking_fee_info) > 0 else '',
              'imageUrls': [image['src'] for image in store_images],
              'providerStoreId': store_id
              }
    return result


def is_valid(document):
    return document['name'] != ''


def main():
    documents = []
    for i in range(1, 2000):
        document = get_document(i)
        if is_valid(document):
            documents.append(document)
    dumps = json.dumps(documents, ensure_ascii=False).encode('utf8')
    with open("result.json", "w") as out_file:
        out_file.write(dumps.decode())


if __name__ == '__main__':
    main()
