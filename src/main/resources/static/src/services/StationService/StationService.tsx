import { ConfigService } from "../Config/ConfigService";
import { IStationInfo } from "../../types/StationTypes";
import * as React from "react";

export namespace StationService {

    export function getStationsInfo(): Promise<any> {
        return fetch(ConfigService.staticStationsInfo).then(results => {
            return results.json();
        });
    }

    export function getStationsEstimates(orig: string): Promise<any> {
        return fetch(ConfigService.staticStationEstimates + "?orig=" + orig).then(results => {
            return results.json();
        });
    }

    export function filterForStation(station: string, stations: IStationInfo[]): IStationInfo {
        for( let index in stations ) {
            if (stations[index].abbr === station) {
                return stations[index];
            }
        }
        return stations[0];
    }

    export function getStationsEstimatesFiltered(props: Readonly<any>) : Promise<any> {
        let params = "?orig=" + props.origin + "&dest=" + props.destination;
        return fetch(ConfigService.staticFilteredEstimates + params).then(results => {
            return results.json();
        });   
    }

    export function outputBartText(station: IStationInfo): JSX.Element {
        if (station) {
            return (
                <div className="bart-text">
                    <div>{station.name} - {station.abbr}</div>
                    <div>{station.address}</div>
                    <div>{station.city}, {station.state} {station.zipcode}</div>
                </div>
            );
        } else {
            return (<div>test</div>);
        }
    }

}