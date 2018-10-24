export interface IStationSectionProps {

}

export interface IStationSectionState {
    stations: IStationDetail[]
    maps: any,
    tooltipStation: IStationDetail | undefined,
    tooltipX: number,
    tooltipY: number,
    tooltipActive: boolean,
    tooltipTextCallback: Function
}

export interface IStation {
    id: number;
    description: string;
    createdAt: string;
    modifiedAt: string;
    etds: IEtd[];
    name: string;
    abbr: string;
}

export interface IStationDetail {
    abbr: string;
    address: string;
    city: string;
    county: string;
    gtfsLatitude: number;
    gtfsLongitude: number;
    name: string;
    state: string;
    zipcode: string;
}

export interface IEtd {
    destination: string;
    abbreviation: string;
    limited: number;
    estimates: IEstimate[];
}

export interface IEstimate {
    minutes: number;
    platform: number;
    direction: string;
    length: number;
    bikeflag: boolean;
    delay: number;
}

export interface IStationsTableProps {
    stations: IStationDetail[];
}

export interface IStationsTableState {
    stations: IStationDetail[];
}